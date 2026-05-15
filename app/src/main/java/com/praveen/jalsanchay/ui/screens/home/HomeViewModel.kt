package com.praveen.jalsanchay.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveen.jalsanchay.data.local.entity.UserEntity
import com.praveen.jalsanchay.repository.RainfallRepository
import com.praveen.jalsanchay.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class HomeUiState(
    val user: UserEntity? = null,
    val totalSaved: Double = 0.0,
    val todaySaved: Double = 0.0,
    val tankFillPercentage: Float = 0f,
    val aiInsight: String = "Loading insights..."
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val rainfallRepository: RainfallRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        combine(
            userRepository.getCurrentUser(),
            rainfallRepository.getTotalWaterSaved(),
            rainfallRepository.getLatestEntry()
        ) { user, totalSaved, latestEntry ->
            val total = totalSaved ?: 0.0
            val fillPercentage = latestEntry?.tankLevel?.toFloat()?.div(100f) ?: 0f
            val todaySaved = latestEntry?.litersSaved ?: 0.0
            
            val insight = when {
                fillPercentage > 0.9f -> "Warning: Your tank may overflow soon. Consider using saved water."
                fillPercentage > 0.5f -> "Great job! You have a good amount of water stored."
                else -> "Keep saving! Wait for the next rainfall to fill your tank."
            }

            HomeUiState(
                user = user,
                totalSaved = total,
                todaySaved = todaySaved,
                tankFillPercentage = fillPercentage,
                aiInsight = insight
            )
        }.onEach { state ->
            _uiState.value = state
        }.launchIn(viewModelScope)
    }
}
