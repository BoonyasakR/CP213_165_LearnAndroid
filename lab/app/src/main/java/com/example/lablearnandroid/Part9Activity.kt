package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.min

class Part9Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    CollapsingToolbarScreen()
                }
            }
        }
    }
}

@Composable
private fun CollapsingToolbarScreen() {
    val listState = rememberLazyListState()
    val collapseProgress by remember {
        derivedStateOf {
            min(1f, listState.firstVisibleItemScrollOffset / 220f)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = 260.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ConceptCard(
                    title = "Concept: Collapsing",
                    body = "Collapsing UI คือการทำ Header ขนาดใหญ่ให้ย่อเป็น Toolbar เมื่อผู้ใช้เลื่อนหน้า โดยอ่านค่า scroll state แล้วนำไปเปลี่ยน height, alpha, scale หรือ translation ของ Composable"
                )
            }
            items(18) { index ->
                ConceptCard(
                    title = "Lesson ${index + 1}",
                    body = "เลื่อนรายการนี้เพื่อดู Header ค่อยๆ หด ข้อความใหญ่จางลง และ Toolbar ด้านบนชัดขึ้นตามค่า collapse progress"
                )
            }
        }

        LargeCollapsingHeader(
            collapseProgress = collapseProgress,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun LargeCollapsingHeader(collapseProgress: Float, modifier: Modifier = Modifier) {
    val height = 260.dp - (172.dp * collapseProgress)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF155E75), Color(0xFF0F766E), Color(0xFF14B8A6))
                )
            )
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .graphicsLayer {
                    scaleX = 1f - (collapseProgress * 0.12f)
                    scaleY = 1f - (collapseProgress * 0.12f)
                }
                .alpha(1f - (collapseProgress * 0.75f))
        ) {
            Text(
                text = "Part 9",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Collapsing Header",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Collapsing",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .alpha(collapseProgress)
        )
    }
}

@Composable
private fun ConceptCard(title: String, body: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, MaterialTheme.shapes.medium)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(body, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF475569))
    }
}
