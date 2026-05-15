package com.praveen.jalsanchay.ui.screens.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.praveen.jalsanchay.ui.components.GlassCard
import com.praveen.jalsanchay.ui.components.GradientButton
import com.praveen.jalsanchay.ui.theme.NeonCyan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataEntryScreen(
    onNavigateBack: () -> Unit,
    viewModel: DataEntryViewModel = hiltViewModel()
) {
    var roofArea by remember { mutableStateOf("") }
    var rainfall by remember { mutableStateOf("") }
    var tankCapacity by remember { mutableStateOf("") }
    var runoffCoefficient by remember { mutableStateOf("0.85") }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.saveSuccess) {
        if (uiState.saveSuccess) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Log Rainwater") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = NeonCyan,
                    navigationIconContentColor = NeonCyan
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        OutlinedTextField(
                            value = roofArea,
                            onValueChange = { roofArea = it },
                            label = { Text("Roof Area (sq ft)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = NeonCyan,
                                focusedLabelColor = NeonCyan
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = rainfall,
                            onValueChange = { rainfall = it },
                            label = { Text("Rainfall (mm)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = NeonCyan,
                                focusedLabelColor = NeonCyan
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = tankCapacity,
                            onValueChange = { tankCapacity = it },
                            label = { Text("Tank Capacity (Liters)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = NeonCyan,
                                focusedLabelColor = NeonCyan
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = runoffCoefficient,
                            onValueChange = { runoffCoefficient = it },
                            label = { Text("Runoff Coefficient (0.0 - 1.0)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = NeonCyan,
                                focusedLabelColor = NeonCyan
                            )
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        if (uiState.errorMessage != null) {
                            Text(
                                text = uiState.errorMessage!!,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }

                        GradientButton(
                            text = "Calculate & Save",
                            onClick = {
                                viewModel.saveEntry(
                                    roofArea = roofArea,
                                    rainfall = rainfall,
                                    tankCapacity = tankCapacity,
                                    runoffCoefficient = runoffCoefficient
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
