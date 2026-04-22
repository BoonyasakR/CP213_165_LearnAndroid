package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class Part11Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    SkeletonLoadingScreen()
                }
            }
        }
    }
}

@Composable
private fun SkeletonLoadingScreen() {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2500)
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Part 11: Skeleton Loading",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Concept: Skeleton loading คือการแสดงโครงร่าง UI ชั่วคราวระหว่างโหลดข้อมูล ทำให้ผู้ใช้เห็นว่าหน้าจอกำลังจะมี content รูปแบบไหน แทนการปล่อยหน้าว่างหรือแสดง spinner อย่างเดียว"
        )

        repeat(5) { index ->
            if (isLoading) {
                SkeletonListItem()
            } else {
                LoadedListItem(index + 1)
            }
        }
    }
}

@Composable
private fun SkeletonListItem() {
    val transition = rememberInfiniteTransition(label = "skeleton")
    val shimmerAlpha by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    val brush = Brush.linearGradient(
        listOf(
            Color(0xFFE2E8F0).copy(alpha = shimmerAlpha),
            Color(0xFFF1F5F9),
            Color(0xFFE2E8F0).copy(alpha = shimmerAlpha)
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.fillMaxWidth(0.7f).height(18.dp).clip(RoundedCornerShape(8.dp)).background(brush))
            Box(modifier = Modifier.fillMaxWidth().height(14.dp).clip(RoundedCornerShape(8.dp)).background(brush))
            Box(modifier = Modifier.fillMaxWidth(0.45f).height(14.dp).clip(RoundedCornerShape(8.dp)).background(brush))
        }
    }
}

@Composable
private fun LoadedListItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(modifier = Modifier.size(54.dp).clip(CircleShape).background(Color(0xFF0F766E)))
        Column(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.weight(1f)) {
            Text("Loaded item $index", fontWeight = FontWeight.Bold)
            Text("ข้อมูลจริงแสดงแทน skeleton หลัง delay สั้นๆ", color = Color(0xFF64748B))
        }
    }
}
