package com.praveen.jalsanchay.ui.screens.tips

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praveen.jalsanchay.ui.components.GlassCard
import com.praveen.jalsanchay.ui.theme.NeonCyan

val waterSavingTips = listOf(
    Pair("Check for Leaks", "A dripping tap can waste 15 liters of water a day, or 5,500 liters of water a year."),
    Pair("Shorter Showers", "Cutting your shower time by just 1 minute can save up to 120 gallons of water per month."),
    Pair("Turn Off the Tap", "Turn off the tap while brushing your teeth to save up to 200 gallons of water a month."),
    Pair("Use a Rain Barrel", "Collect rainwater from your roof to water your garden, saving hundreds of gallons."),
    Pair("Water Plants Early", "Water your outdoor plants early in the morning to reduce evaporation.")
)

@Composable
fun TipsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "Conservation Tips",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 32.dp.value.sp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(waterSavingTips) { tip ->
                    TipCard(title = tip.first, description = tip.second)
                }
            }
        }
    }
}

@Composable
fun TipCard(title: String, description: String) {
    var expanded by remember { mutableStateOf(false) }

    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = NeonCyan,
                fontWeight = FontWeight.Bold
            )
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
