package com.example.lablearnandroid

import android.content.Intent
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
import androidx.core.app.ActivityOptionsCompat

class TransitionDemoMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    TransitionDemoMainScreen(
                        onOpenDetail = {
                            val intent = Intent(this, TransitionDemoDetailActivity::class.java)
                                .putExtra(TransitionDemoDetailActivity.EXTRA_MESSAGE, "Opened from MainActivity with slide-up transition")
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this,
                                R.anim.slide_in_bottom,
                                R.anim.hold
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
private fun TransitionDemoMainScreen(onOpenDetail: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFEEF2FF), Color(0xFFDDEAFE)))
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Mission 7: Activity Transition Demo",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Open the detail screen with startActivity and a custom slide-up animation.",
                textAlign = TextAlign.Center,
                color = Color(0xFF475569)
            )
            Button(onClick = onOpenDetail) {
                Text("Open Detail")
            }
        }
    }
}
