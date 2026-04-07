package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactPaginationActivity : ComponentActivity() {
    private val viewModel by viewModels<ContactPaginationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    ContactPaginationScreen(viewModel = viewModel)
                }
            }
        }
    }
}

class ContactPaginationViewModel : ViewModel() {
    private val allContacts = buildMockContacts()
    private val pageSize = 18
    private var currentIndex = 0

    val contacts = mutableStateListOf<String>()
    var isLoading by mutableStateOf(false)
        private set

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading || currentIndex >= allContacts.size) return

        viewModelScope.launch {
            isLoading = true
            delay(2_000)

            val nextIndex = (currentIndex + pageSize).coerceAtMost(allContacts.size)
            contacts.addAll(allContacts.subList(currentIndex, nextIndex))
            currentIndex = nextIndex
            isLoading = false
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContactPaginationScreen(viewModel: ContactPaginationViewModel) {
    val groupedContacts = viewModel.contacts.groupBy { it.first().uppercaseChar() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Contact List Pagination") })
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8FAFC),
                            Color(0xFFE2E8F0)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                groupedContacts.forEach { (header, names) ->
                    stickyHeader {
                        ContactHeader(letter = header.toString())
                    }

                    itemsIndexed(
                        items = names,
                        key = { _, name -> name }
                    ) { index, name ->
                        val isLastVisibleItem =
                            header == groupedContacts.keys.lastOrNull() &&
                                index == names.lastIndex

                        if (isLastVisibleItem) {
                            viewModel.loadNextPage()
                        }

                        ContactRow(name = name)
                    }
                }

                if (viewModel.isLoading) {
                    item(key = "loading_footer") {
                        LoadingFooter()
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactHeader(letter: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD8B4FE))
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(
            text = letter,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3B0764)
        )
    }
}

@Composable
private fun ContactRow(name: String) {
    var visible by remember(name) { mutableStateOf(false) }

    LaunchedEffect(name) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFDFFFFFF))
        ) {
            Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = "Tap-free mock contact entry",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
private fun LoadingFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(28.dp))
    }
}

private fun buildMockContacts(): List<String> {
    val samplesByLetter = mapOf(
        'A' to listOf("Aom", "Alice", "Anan", "Aree", "Atom"),
        'B' to listOf("Bank", "Bella", "Ben", "Best", "Bim"),
        'C' to listOf("Cake", "Captain", "Cee", "Champ", "Chompoo"),
        'D' to listOf("Dan", "Dao", "Dear", "Dome", "Dream"),
        'E' to listOf("Earth", "Eve", "Ekk", "Em", "Earn"),
        'F' to listOf("Fah", "Fang", "Fern", "Film", "First"),
        'G' to listOf("Game", "Gift", "Ging", "Golf", "Grace"),
        'H' to listOf("Heart", "Him", "Hong", "Hope", "Hwa"),
        'I' to listOf("Ice", "Iris", "Ink", "Int", "Ivy"),
        'J' to listOf("Jane", "Jao", "Jay", "Jib", "Jin"),
        'K' to listOf("Kai", "Karn", "Kat", "Ken", "Korn"),
        'L' to listOf("Lek", "Lily", "Lin", "Lita", "Luke"),
        'M' to listOf("Mai", "Mint", "Mek", "May", "Mild"),
        'N' to listOf("Nam", "Nan", "Nat", "New", "Nine"),
        'O' to listOf("Oak", "Oat", "Oil", "On", "Opal"),
        'P' to listOf("Pair", "Palm", "Pan", "Pear", "Pete"),
        'Q' to listOf("Qeen", "Qim", "Qraft", "Qwin", "Qyu"),
        'R' to listOf("Rain", "Ray", "Rin", "Rome", "Ruby"),
        'S' to listOf("Sai", "Sand", "Say", "Som", "Sun"),
        'T' to listOf("Tan", "Tar", "Team", "Tle", "Ton"),
        'U' to listOf("Uma", "Un", "Unit", "Up", "Uri"),
        'V' to listOf("Val", "Van", "Vee", "Verse", "Violet"),
        'W' to listOf("Wan", "Wave", "Weir", "Win", "Wit"),
        'X' to listOf("Xen", "Xim", "Xing", "Xtra", "Xyrus"),
        'Y' to listOf("Yai", "Yam", "Ying", "Yok", "Yuri"),
        'Z' to listOf("Zee", "Zen", "Zero", "Zin", "Zom")
    )

    return samplesByLetter
        .toSortedMap()
        .flatMap { (letter, names) ->
            names.mapIndexed { index, name -> "$name $letter${index + 1}" }
        }
}

@Preview(showBackground = true)
@Composable
private fun ContactPaginationPreview() {
    MaterialTheme {
        ContactPaginationScreen(viewModel = ContactPaginationViewModel())
    }
}
