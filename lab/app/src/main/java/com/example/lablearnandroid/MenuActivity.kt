package com.example.lablearnandroid

import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lablearnandroid.architecture.mvi.MviCounterActivity
import com.example.lablearnandroid.architecture.mvvm.MvvmCounterActivity

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuScreen(
                onOpenCameraFlow = {
                    startActivity(Intent(this@MenuActivity, CameraPermissionActivity::class.java))
                },
                onOpenSensorStream = {
                    startActivity(Intent(this@MenuActivity, SensorStreamActivity::class.java))
                },
                onOpenRpg = { startActivity(Intent(this@MenuActivity, RPGCardActivity::class.java)) },
                onOpenPokedex = { startActivity(Intent(this@MenuActivity, PokedexActivity::class.java)) },
                onOpenLifecycle = {
                    startActivity(Intent(this@MenuActivity, LifeCycleComposeActivity::class.java))
                },
                onOpenMvvm = {
                    startActivity(Intent(this@MenuActivity, MvvmCounterActivity::class.java))
                },
                onOpenMvi = { startActivity(Intent(this@MenuActivity, MviCounterActivity::class.java)) },
                onOpenSharedPrefs = {
                    startActivity(Intent(this@MenuActivity, SharedPreferencesActivity::class.java))
                },
                onOpenLikeAnimation = {
                    startActivity(Intent(this@MenuActivity, LikeAnimationActivity::class.java))
                },
                onOpenContactPagination = {
                    startActivity(Intent(this@MenuActivity, ContactPaginationActivity::class.java))
                },
                onOpenDonutChart = {
                    startActivity(Intent(this@MenuActivity, DonutChartActivity::class.java))
                },
                onOpenSwipeTodo = {
                    startActivity(Intent(this@MenuActivity, SwipeToDismissTodoActivity::class.java))
                },
                onOpenSnackbarSideEffect = {
                    startActivity(Intent(this@MenuActivity, SnackbarSideEffectActivity::class.java))
                },
                onOpenWebViewInterop = {
                    startActivity(Intent(this@MenuActivity, WebViewInteropActivity::class.java))
                },
                onOpenTransitionDemo = {
                    startActivity(Intent(this@MenuActivity, TransitionDemoMainActivity::class.java))
                },
                onOpenAdaptiveProfile = {
                    startActivity(Intent(this@MenuActivity, AdaptiveProfileActivity::class.java))
                },
                onOpenPart9 = {
                    startActivity(Intent(this@MenuActivity, Part9Activity::class.java))
                },
                onOpenPart10 = {
                    startActivity(Intent(this@MenuActivity, Part10Activity::class.java))
                },
                onOpenPart11 = {
                    startActivity(Intent(this@MenuActivity, Part11Activity::class.java))
                },
                onOpenPart12 = {
                    startActivity(Intent(this@MenuActivity, Part12Activity::class.java))
                },
            )
        }
    }
}

@Composable
private fun MenuScreen(
    onOpenCameraFlow: () -> Unit,
    onOpenSensorStream: () -> Unit,
    onOpenRpg: () -> Unit,
    onOpenPokedex: () -> Unit,
    onOpenLifecycle: () -> Unit,
    onOpenMvvm: () -> Unit,
    onOpenMvi: () -> Unit,
    onOpenSharedPrefs: () -> Unit,
    onOpenLikeAnimation: () -> Unit,
    onOpenContactPagination: () -> Unit,
    onOpenDonutChart: () -> Unit,
    onOpenSwipeTodo: () -> Unit,
    onOpenSnackbarSideEffect: () -> Unit,
    onOpenWebViewInterop: () -> Unit,
    onOpenTransitionDemo: () -> Unit,
    onOpenAdaptiveProfile: () -> Unit,
    onOpenPart9: () -> Unit,
    onOpenPart10: () -> Unit,
    onOpenPart11: () -> Unit,
    onOpenPart12: () -> Unit,
) {
    val menuItems = listOf(
        "Camera Permission Flow" to onOpenCameraFlow,
        "Sensor Stream MVVM" to onOpenSensorStream,
        "RPG Card" to onOpenRpg,
        "Pokedex" to onOpenPokedex,
        "Compose Lifecycle" to onOpenLifecycle,
        "MVVM Counter" to onOpenMvvm,
        "MVI Counter" to onOpenMvi,
        "SharedPreferences" to onOpenSharedPrefs,
        "Mission 1: Compose Like Animation" to onOpenLikeAnimation,
        "Mission 2: Contacts Sticky Pagination" to onOpenContactPagination,
        "Mission 3: Canvas Donut Chart" to onOpenDonutChart,
        "Mission 4: Swipe To Dismiss To-Do" to onOpenSwipeTodo,
        "Mission 5: Snackbar Side Effects" to onOpenSnackbarSideEffect,
        "Mission 6: WebView Interop" to onOpenWebViewInterop,
        "Mission 7: Activity Transition Demo" to onOpenTransitionDemo,
        "Mission 8: Adaptive Profile Layout" to onOpenAdaptiveProfile,
        "Mission 9: Collapsing Header" to onOpenPart9,
        "Mission 10: App Widget with Glance" to onOpenPart10,
        "Mission 11: Skeleton Loading" to onOpenPart11,
        "Mission 12: Bottom Sheet and Dialog" to onOpenPart12,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B))))
            .padding(16.dp)
    ) {
        Text(
            text = "Lab Learn Android",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFE2E8F0),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(menuItems) { (title, action) ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xCC334155)),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = action,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(title)
                    }
                }
            }
        }
    }
}
