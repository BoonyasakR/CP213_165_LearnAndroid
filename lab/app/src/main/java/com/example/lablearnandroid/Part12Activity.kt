package com.example.lablearnandroid

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class Part12Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    SheetAndDialogScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SheetAndDialogScreen() {
    var showSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFF8FAFC), Color(0xFFE0F2FE))))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Part 12: Modal Bottom Sheet & Middle Dialog",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Concept: Modal Bottom Sheet เหมาะกับ action หรือ detail ที่สัมพันธ์กับหน้าปัจจุบันและเลื่อนขึ้นจากด้านล่าง ส่วน Middle Dialog เหมาะกับการยืนยันหรือแจ้งเตือนที่ต้องการให้ผู้ใช้ตัดสินใจทันที"
        )
        Button(onClick = { showSheet = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Open Modal Bottom Sheet")
        }
        Button(onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Open Middle Dialog")
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Bottom Sheet", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("ใช้สำหรับตัวเลือกเพิ่มเติม เช่น share, filter, sort หรือรายละเอียดที่ไม่จำเป็นต้องเปิด Activity ใหม่")
                Button(onClick = { showSheet = false }, modifier = Modifier.fillMaxWidth()) {
                    Text("Close Sheet")
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Middle Dialog") },
            text = { Text("Dialog กลางจอใช้กับงานที่ต้องการ confirmation เช่น ลบข้อมูล ออกจากระบบ หรือแจ้ง error สำคัญ") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
