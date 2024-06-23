package com.xtl.ebusiness.common.questionAnswer.page

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.xtl.ebusiness.entity.ResponseRateData
import com.xtl.ebusiness.pdd.responserate.funtion.addResponseRateData
import pdd.responserate.data.ResponseRateTypeOptions
import pdd.responserate.data.SimulatorTypeOptions
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionAnswerDialog(
    onClose: () -> Unit,
    refreshData: () -> Unit
){
    var simulatorType by remember { mutableStateOf("") }
    var simulatorName by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var chatName by remember { mutableStateOf("") }
    var expandedType by remember { mutableStateOf(false) }
    var expandedSimulatorType by remember { mutableStateOf(false) }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var showAddDate by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    Column(

    ) {
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = simulatorName,
            onValueChange = { simulatorName = it },
            label = { Text("问题") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = chatName,
            onValueChange = { chatName = it },
            label = { Text("答案") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 按钮行
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onClose) {
                Text("取消")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                val responseRateData = ResponseRateData().apply {
                    this.simulatorType = simulatorType.toInt()
                    this.simulatorName = simulatorName
                    this.roleType = type.toInt()
                    this.chatName = chatName
                }
                val success = addResponseRateData(responseRateData)
                dialogMessage = if (success) {
                    "添加成功"
                } else {
                    "添加失败"
                }
                showAddDate = true
            }) {
                Text("添加")
            }
        }

        if (showAddDate) {
            AlertDialog(
                onDismissRequest = { showAddDate = false },
                confirmButton = {
                    TextButton(onClick = { showAddDate = false; if (dialogMessage == "添加成功") onClose() }) {
                        Text("确定")
                    }
                },
                title = { Text("结果") },
                text = { Text(dialogMessage) }
            )
            refreshData()
        }
    }
}
