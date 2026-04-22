package com.example.lablearnandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column as GlanceColumn
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight as GlanceFontWeight
import androidx.glance.text.Text as GlanceText
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class Part10Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    AppWidgetConceptScreen()
                }
            }
        }
    }
}

@Composable
private fun AppWidgetConceptScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFF8FAFC), Color(0xFFDDE7F0))))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Part 10: App Widget with Jetpack Glance",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Concept: App Widget คือ UI ขนาดเล็กบน Home Screen ของ Android. Jetpack Glance ให้เราเขียน Widget ด้วย API ที่คล้าย Compose แต่ไม่ใช่ Compose UI เต็มรูปแบบ เพราะ Widget ถูก render ผ่าน RemoteViews ของระบบ"
        )
        Text(
            text = "ไฟล์นี้มีทั้ง Activity สำหรับอ่าน concept และคลาส LearnGlanceWidget/LearnGlanceWidgetReceiver สำหรับประกาศ Widget จริงใน Manifest"
        )
        Text(
            text = "วิธีทดสอบ: Build แอป จากนั้น long press ที่ Home Screen > Widgets > เลือก LabLearnAndroid Widget แล้ววางลงหน้าจอ"
        )
    }
}

class LearnGlanceWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            LearnGlanceWidgetContent()
        }
    }
}

class LearnGlanceWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = LearnGlanceWidget()
}

@Composable
private fun LearnGlanceWidgetContent() {
    GlanceColumn(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(Color(0xFF0F766E)))
            .padding(16.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.Start
    ) {
        GlanceText(
            text = "Learn Android",
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontWeight = GlanceFontWeight.Bold
            )
        )
        GlanceText(
            text = "Jetpack Glance widget example",
            style = TextStyle(color = ColorProvider(Color(0xFFE0F2FE)))
        )
    }
}
