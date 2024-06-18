package pdd.responserate.page

import DraggableAddDeviceDialogContent
import TableFormObj
import TableHeadObj
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import pdd.responserate.data.ResponseRateTypeOptions
import pdd.responserate.data.loadResponseRateData
import ui.theme.AppTheme


@Composable
fun HomeScreen() {


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        val tableHead = mapOf(
            "type" to TableHeadObj(description = "类型", width = 120.dp),
            "deviceId" to TableHeadObj(description = "设备ID", width = 160.dp),
            "chatName" to TableHeadObj(description = "聊天对象名称", width = 200.dp),
            "switch" to TableHeadObj(description = "开关")
        )

        val tableForm = mapOf(
            "type" to TableFormObj(type = FormFieldType.Dropdown(options = ResponseRateTypeOptions)),
            "deviceId" to TableFormObj(fieldName = "userName", type = FormFieldType.EditableText),
            "chatName" to TableFormObj(fieldName = "userName", type = FormFieldType.EditableText),
            "switch" to TableFormObj(
                type = FormFieldType.SwitchButton { data ->
                    println("SwitchButton clicked for: $data")
                }
            )
        )

        var showAddDeviceDialog by remember { mutableStateOf(false) }

        TableUtils.EditableTable(
            tableHead = tableHead,
            tableForm = tableForm,
            loadData = { page, pageSize -> loadResponseRateData(page, pageSize) },
            onSave = { data ->
                val dataList = data.toList()
                println("Current data: $dataList")
            },
            onDelete = { data ->
                println(data)
            },
            onAdd = {
                showAddDeviceDialog = true
            }
        )

        if (showAddDeviceDialog) {
            val windowState = rememberWindowState(
                width = 450.dp,
                height = Dp.Unspecified
            )
            Window(
                onCloseRequest = { showAddDeviceDialog = false },
                title = "新增拼多多自动回复",
                state = windowState
            ) {
                AppTheme {
                    DraggableAddDeviceDialogContent(onClose = { showAddDeviceDialog = false })
                }
            }
        }
    }
}