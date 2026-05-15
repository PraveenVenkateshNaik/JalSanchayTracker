package com.praveen.jalsanchay.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveen.jalsanchay.data.datastore.UserPreferences
import com.praveen.jalsanchay.data.local.entity.UserEntity
import com.praveen.jalsanchay.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun loginOrRegister(username: String, passwordHash: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                var user = userRepository.getUserByUsername(username)
                if (user == null) {
                    // Register new user
                    val newUser = UserEntity(username = username, passwordHash = passwordHash)
                    val id = userRepository.insertUser(newUser)
                    userPreferences.setLoggedIn(id.toInt())
                } else {
                    if (user.passwordHash == passwordHash) {
                        userPreferences.setLoggedIn(user.id)
                    } else {
                        _loginState.value = LoginState.Error("Invalid credentials")
                        return@launch
                    }
                }
                _loginState.value = LoginState.Success
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
