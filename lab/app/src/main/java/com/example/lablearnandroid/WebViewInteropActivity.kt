package com.example.lablearnandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class WebViewInteropActivity : ComponentActivity() {
    private val viewModel by viewModels<WebViewInteropViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    WebViewInteropScreen(viewModel)
                }
            }
        }
    }
}

class WebViewInteropViewModel : ViewModel() {
    var currentUrl by mutableStateOf("https://www.google.com")
        private set

    fun updateUrl(rawUrl: String) {
        val normalized = rawUrl.trim().let { input ->
            when {
                input.isBlank() -> currentUrl
                input.startsWith("http://") || input.startsWith("https://") -> input
                else -> "https://$input"
            }
        }
        currentUrl = normalized
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewInteropScreen(viewModel: WebViewInteropViewModel) {
    var inputUrl by rememberSaveable { mutableStateOf(viewModel.currentUrl) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mission 6: WebView Interop") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = inputUrl,
                    onValueChange = { inputUrl = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("URL") },
                    singleLine = true
                )
                Button(
                    onClick = { viewModel.updateUrl(inputUrl) },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Go")
                }
            }

            Text(
                text = "Current URL: ${viewModel.currentUrl}",
                style = MaterialTheme.typography.bodySmall
            )

            WebViewContainer(
                url = viewModel.currentUrl,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun WebViewContainer(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl(url)
            }
        },
        update = { webView ->
            if (webView.url != url) {
                webView.loadUrl(url)
            }
        }
    )
}
