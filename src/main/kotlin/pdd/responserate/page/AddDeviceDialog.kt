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
import androidx.compose.ui.unit.*
import pdd.responserate.data.ResponseRateTypeOptions
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableAddDeviceDialogContent(onClose: () -> Unit) {
    var type by remember { mutableStateOf("") }
    var deviceId by remember { mutableStateOf("") }
    var chatName by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var showAddDate by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = ResponseRateTypeOptions.find { it.first == type }?.second ?: "请选择类型",
                onValueChange = {},
                label = { Text("类型") },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ResponseRateTypeOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.second) },
                        onClick = {
                            type = option.first
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = deviceId,
            onValueChange = { deviceId = it },
            label = { Text("设备ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = chatName,
            onValueChange = { chatName = it },
            label = { Text("聊天对象名称") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onClose) {
                Text("取消")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                val success = addResponseRateData(type, deviceId, chatName)
                dialogMessage = if (success) "添加成功" else "添加失败"
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
        }
    }
}

fun addResponseRateData(type: String, deviceId: String, chatName: String): Boolean {
    // 这里是添加响应率数据的逻辑
    return try {
        println("添加数据: 类型=$type, 设备ID=$deviceId, 聊天对象名称=$chatName")
        true // 假设成功
    } catch (e: Exception) {
        e.printStackTrace()
        false // 假设失败
    }
}