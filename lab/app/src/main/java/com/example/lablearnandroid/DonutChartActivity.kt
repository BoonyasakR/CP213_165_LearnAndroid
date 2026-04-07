package com.example.lablearnandroid

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

class DonutChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    DonutChartScreen()
                }
            }
        }
    }
}

@Composable
fun DonutChartScreen() {
    val chartValues = listOf(30f, 40f, 30f)
    val chartColors = listOf(
        Color(0xFFFB7185),
        Color(0xFF38BDF8),
        Color(0xFFFBBF24)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0F172A), Color(0xFF111827), Color(0xFF1E293B))
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Canvas Donut Chart",
                color = Color(0xFFE2E8F0),
                style = MaterialTheme.typography.headlineSmall
            )

            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .clip(CircleShape)
                        .blur(18.dp)
                        .background(Color(0x2238BDF8))
                )

                DonutChart(
                    values = chartValues,
                    colors = chartColors,
                    modifier = Modifier.size(220.dp)
                )

                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = 0.94f
                            scaleX = 0.96f
                            scaleY = 0.96f
                            rotationZ = -4f
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                renderEffect = RenderEffect.createBlurEffect(
                                    6f,
                                    6f,
                                    Shader.TileMode.CLAMP
                                ).asComposeRenderEffect()
                            }
                        },
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xCC0F172A))
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("100%", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Text("Traffic Mix", color = Color(0xFF94A3B8))
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                chartValues.zip(chartColors).forEachIndexed { index, (value, color) ->
                    LegendChip(
                        label = "Part ${index + 1}",
                        value = "${value.toInt()}%",
                        color = color
                    )
                }
            }
        }
    }
}

@Composable
fun DonutChart(
    values: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val total = values.sum().takeIf { it > 0f } ?: 1f
    val progress = remember { Animatable(0f) }

    LaunchedEffect(values) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1400, easing = FastOutSlowInEasing)
        )
    }

    Canvas(modifier = modifier) {
        val strokeWidth = size.minDimension * 0.18f
        val diameter = min(size.width, size.height)
        val topLeft = androidx.compose.ui.geometry.Offset(
            x = (size.width - diameter) / 2f,
            y = (size.height - diameter) / 2f
        )
        val arcSize = androidx.compose.ui.geometry.Size(diameter, diameter)

        var startAngle = -90f
        val animatedSweepTotal = 360f * progress.value

        values.forEachIndexed { index, value ->
            val sliceSweep = 360f * (value / total)
            val drawnSweep = (animatedSweepTotal - (startAngle + 90f)).coerceIn(0f, sliceSweep)

            if (drawnSweep > 0f) {
                drawArc(
                    color = colors[index % colors.size],
                    startAngle = startAngle,
                    sweepAngle = drawnSweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            startAngle += sliceSweep
        }
    }
}

@Composable
private fun LegendChip(label: String, value: String, color: Color) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Column {
            Text(label, color = Color(0xFFCBD5E1), style = MaterialTheme.typography.bodySmall)
            Text(value, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DonutChartScreenPreview() {
    MaterialTheme {
        DonutChartScreen()
    }
}
