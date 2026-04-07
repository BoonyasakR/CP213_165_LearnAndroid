package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class TransitionDemoDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val message = intent.getStringExtra(EXTRA_MESSAGE).orEmpty()

        setContent {
            MaterialTheme {
                Surface {
                    TransitionDemoDetailScreen(
                        message = message,
                        onClose = {
                            finish()
                            overridePendingTransition(R.anim.hold, R.anim.slide_out_bottom)
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}

@androidx.compose.runtime.Composable
private fun TransitionDemoDetailScreen(message: String, onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B)))
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "DetailActivity",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = message,
                color = Color(0xFFCBD5E1),
                textAlign = TextAlign.Center
            )
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    }
}
