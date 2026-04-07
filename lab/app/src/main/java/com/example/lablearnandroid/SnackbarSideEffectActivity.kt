package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SnackbarSideEffectActivity : ComponentActivity() {
    private val viewModel by viewModels<SnackbarSideEffectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    SnackbarSideEffectScreen(viewModel)
                }
            }
        }
    }
}

class SnackbarSideEffectViewModel : ViewModel() {
    private val _errorEvents = MutableSharedFlow<String>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errorEvents = _errorEvents.asSharedFlow()

    fun triggerError() {
        viewModelScope.launch {
            _errorEvents.emit("Network request failed. Please try again.")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarSideEffectScreen(viewModel: SnackbarSideEffectViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.errorEvents.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            // Placeholder for cleanup when this screen leaves composition.
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Compose Side Effects") }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFECFDF5), Color(0xFFD1FAE5))
                    )
                )
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Trigger an error event from ViewModel.\nLaunchedEffect collects it and shows a Snackbar as a one-time side effect.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF14532D)
                    )
                    Button(onClick = viewModel::triggerError) {
                        Text("Trigger Error")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnackbarSideEffectPreview() {
    MaterialTheme {
        SnackbarSideEffectScreen(viewModel = SnackbarSideEffectViewModel())
    }
}
