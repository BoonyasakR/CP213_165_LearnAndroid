package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class LikeAnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    LikeAnimationScreen()
                }
            }
        }
    }
}

@Composable
fun LikeAnimationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF7FB),
                        Color(0xFFFFE4EF)
                    )
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        LikeButtonDemo()
    }
}

@Composable
fun LikeButtonDemo() {
    var isLiked by remember { mutableStateOf(false) }
    var pulseActive by remember { mutableStateOf(false) }

    LaunchedEffect(pulseActive) {
        if (pulseActive) {
            delay(180)
            pulseActive = false
        }
    }

    val scale by animateFloatAsState(
        targetValue = if (pulseActive) 1.12f else 1f,
        animationSpec = spring(
            dampingRatio = 0.45f,
            stiffness = 420f
        ),
        label = "like_button_scale"
    )

    val containerColor by androidx.compose.animation.animateColorAsState(
        targetValue = if (isLiked) Color(0xFFFF5C93) else Color(0xFFD1D5DB),
        animationSpec = tween(durationMillis = 350),
        label = "like_button_color"
    )

    Button(
        onClick = {
            isLiked = !isLiked
            pulseActive = true
        },
        modifier = Modifier.scale(scale),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = if (isLiked) "Liked" else "Like",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 6.dp)
            )

            AnimatedVisibility(
                visible = isLiked,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { it / 2 }) + scaleIn(),
                exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it / 2 }) + scaleOut()
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Liked heart",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LikeAnimationScreenPreview() {
    MaterialTheme {
        LikeAnimationScreen()
    }
}
