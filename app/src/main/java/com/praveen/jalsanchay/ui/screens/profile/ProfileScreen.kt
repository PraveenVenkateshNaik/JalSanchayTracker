package com.praveen.jalsanchay.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.praveen.jalsanchay.ui.components.GlassCard
import com.praveen.jalsanchay.ui.components.GradientButton
import com.praveen.jalsanchay.ui.theme.NeonCyan

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 32.dp.value.sp),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(32.dp))

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "👤",
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.dp.value.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = user?.username ?: "Guest",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonCyan
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Eco Score: ${user?.ecoScore ?: 0}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            GradientButton(
                text = "Logout",
                onClick = {
                    viewModel.logout()
                    onLogout()
                }
            )
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
