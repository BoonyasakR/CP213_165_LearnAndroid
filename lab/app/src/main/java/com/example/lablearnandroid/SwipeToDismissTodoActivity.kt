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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class SwipeToDismissTodoActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    SwipeToDismissTodoScreen(viewModel)
                }
            }
        }
    }
}

class TodoListViewModel : ViewModel() {
    val todos = mutableStateListOf(
        "Review Compose animation notes",
        "Build sticky header contact list",
        "Refactor screen state to ViewModel",
        "Test pagination loading footer",
        "Polish swipe-to-dismiss UX"
    )

    fun removeTodo(todo: String) {
        todos.remove(todo)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissTodoScreen(viewModel: TodoListViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Swipe To Dismiss To-Do") }) },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFF8FAFC), Color(0xFFE2E8F0))
                    )
                )
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    items = viewModel.todos,
                    key = { todo -> todo }
                ) { todo ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = { newValue ->
                            if (newValue == SwipeToDismissBoxValue.EndToStart) {
                                viewModel.removeTodo(todo)
                                true
                            } else {
                                false
                            }
                        },
                        positionalThreshold = { distance -> distance * 0.7f }
                    )

                    SwipeToDismissBox(
                        state = dismissState,
                        enableDismissFromStartToEnd = false,
                        backgroundContent = {
                            val isDismissed = dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (isDismissed) Color(0xFFDC2626) else Color(0xFFFCA5A5),
                                        RoundedCornerShape(18.dp)
                                    )
                                    .padding(horizontal = 20.dp, vertical = 22.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete task",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    ) {
                        TodoRow(todo = todo)
                    }
                }
            }
        }
    }
}

@Composable
private fun TodoRow(todo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp)) {
            Text(
                text = todo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0F172A)
            )
            Text(
                text = "Swipe left to remove this item",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF64748B)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SwipeToDismissTodoPreview() {
    MaterialTheme {
        SwipeToDismissTodoScreen(viewModel = TodoListViewModel())
    }
}
