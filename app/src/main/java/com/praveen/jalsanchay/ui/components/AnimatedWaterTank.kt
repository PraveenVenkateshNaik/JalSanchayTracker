package com.praveen.jalsanchay.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.praveen.jalsanchay.ui.theme.NeonCyan
import com.praveen.jalsanchay.ui.theme.WaterBlue
import kotlin.math.sin

@Composable
fun AnimatedWaterTank(
    fillPercentage: Float, // 0.0 to 1.0
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "waveOffset"
    )

    val animatedFill by animateFloatAsState(
        targetValue = fillPercentage,
        animationSpec = tween(1500, easing = FastOutSlowInEasing),
        label = "fill"
    )

    Box(
        modifier = modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Draw Tank Outline
            drawCircle(
                color = NeonCyan.copy(alpha = 0.3f),
                radius = canvasWidth / 2,
                center = Offset(canvasWidth / 2, canvasHeight / 2),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx())
            )

            // Clip to circle and draw water
            val circlePath = Path().apply {
                addOval(Rect(0f, 0f, canvasWidth, canvasHeight))
            }

            clipPath(circlePath) {
                val waterPath = Path()
                val waveHeight = 15f
                val waterLevel = canvasHeight * (1f - animatedFill)

                waterPath.moveTo(0f, canvasHeight)
                waterPath.lineTo(0f, waterLevel)

                for (x in 0..canvasWidth.toInt() step 5) {
                    val y = waterLevel + waveHeight * sin((x.toFloat() / canvasWidth) * 4f * Math.PI.toFloat() + waveOffset)
                    waterPath.lineTo(x.toFloat(), y)
                }

                waterPath.lineTo(canvasWidth, canvasHeight)
                waterPath.close()

                drawPath(
                    path = waterPath,
                    color = WaterBlue.copy(alpha = 0.8f)
                )
            }
        }
        
        Text(
            text = "${(animatedFill * 100).toInt()}%",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
