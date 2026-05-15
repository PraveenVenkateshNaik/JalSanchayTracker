package com.praveen.jalsanchay.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import com.praveen.jalsanchay.ui.components.GlassCard
import com.praveen.jalsanchay.ui.theme.NeonCyan

@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel = hiltViewModel()
) {
    val entries by viewModel.entries.collectAsState(initial = emptyList())

    val chartEntryModelProducer = ChartEntryModelProducer()
    if (entries.isNotEmpty()) {
        val chartEntries = entries.take(7).reversed().mapIndexed { index, entry ->
            entryOf(index.toFloat(), entry.litersSaved.toFloat())
        }
        chartEntryModelProducer.setEntries(chartEntries)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Water Savings Report",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 32.dp.value.sp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Recent Savings (Liters)",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonCyan
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (entries.isNotEmpty()) {
                        Chart(
                            chart = columnChart(),
                            chartModelProducer = chartEntryModelProducer,
                            startAxis = rememberStartAxis(),
                            bottomAxis = rememberBottomAxis(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    } else {
                        Text("No data available for chart.")
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
