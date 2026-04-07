package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class AdaptiveProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    AdaptiveProfileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdaptiveProfileScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mission 8: Adaptive Layout") }) }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(Color(0xFFF8FAFC), Color(0xFFE2E8F0)))
                )
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            val isCompact = maxWidth < 600.dp

            if (isCompact) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProfileArt(modifier = Modifier.fillMaxWidth().height(220.dp))
                    ProfileInfo(modifier = Modifier.fillMaxWidth())
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProfileArt(modifier = Modifier.weight(1f).fillMaxHeight())
                    ProfileInfo(modifier = Modifier.weight(1.4f).fillMaxHeight())
                }
            }
        }
    }
}

@Composable
private fun ProfileArt(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE5E7EB))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(listOf(Color(0xFFD1D5DB), Color(0xFF9CA3AF)))
                )
        )
    }
}

@Composable
private fun ProfileInfo(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Profile Overview", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Name: Alex Tan")
            Text("Role: Android Developer")
            Text("Location: Bangkok")
            Text(
                "Personal Info: This block is intentionally text-heavy so orientation changes clearly show the adaptive switch between Column and Row layouts."
            )
            Text(
                "Resize or rotate the device. Below 600.dp width the profile image stays on top. At 600.dp and above the screen becomes a side-by-side master-detail layout."
            )
        }
    }
}
