package com.xtl.ebusiness.common.windowBar.page

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionBank
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.xtl.ebusiness.pdd.responserate.funtion.*
import pdd.responserate.data.ResponseRateDataKot
import pdd.responserate.data.ResponseRateTypeOptions
import pdd.responserate.data.SimulatorTypeOptions
import ui.theme.AppTheme

enum class NavigationItem(val title: String, val icon: ImageVector) {
    QUESTION_BANK("问题库", Icons.Filled.QuestionBank),
    VM_ENV_SETUP("模拟器路径", Icons.Filled.Settings)
}

@Composable
fun SettingPage() {
    var selectedItem by remember { mutableStateOf(NavigationItem.QUESTION_BANK) }

    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier.fillMaxHeight(),
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                NavigationItem.values().forEach { item ->
                    NavigationRailItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == item,
                        onClick = { selectedItem = item }
                    )
                }
            }
        }
        ContentPanel(selectedItem = selectedItem)
    }
}

@Composable
fun ContentPanel(selectedItem: NavigationItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        when (selectedItem) {
            NavigationItem.QUESTION_BANK -> QuestionBankContent()
            NavigationItem.VM_ENV_SETUP -> VmEnvSetupContent()
        }
    }
}

@Composable
fun QuestionBankContent() {

    var showAddQuestionDialog by remember { mutableStateOf(false) }
    var refreshData by remember { mutableStateOf<(() -> Unit)?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        val tableHead = mapOf(
            "simulatorType" to TableHeadObj(description = "问题", width = 120.dp),
            "simulatorName" to TableHeadObj(description = "答案", width = 160.dp),
            "roleType" to TableHeadObj(description = "角色类型", width = 120.dp),
            "chatName" to TableHeadObj(description = "聊天对象名称", width = 200.dp),
            "switch" to TableHeadObj(description = "开关")
        )

        val tableForm = mapOf(
            "simulatorType" to TableFormObj(type = FormFieldType.Dropdown(options = SimulatorTypeOptions)),
            "simulatorName" to TableFormObj(type = FormFieldType.EditableText),
            "roleType" to TableFormObj(type = FormFieldType.Dropdown(options = ResponseRateTypeOptions)),
            "chatName" to TableFormObj(type = FormFieldType.EditableText),
            "switch" to TableFormObj(
                type = FormFieldType.SwitchButton(
                    onEnable = { data ->
                        val responseRateDataKot = data as ResponseRateDataKot
                        startTask(responseRateDataKot)
                    },
                    onDisable = { data ->
                        val responseRateDataKot = data as ResponseRateDataKot
                        stopTask(responseRateDataKot)
                    }
                )
            )
        )

        TableUtils.EditableTable(
            tableHead = tableHead,
            tableForm = tableForm,
            loadData = { page, pageSize -> loadResponseRateData(page, pageSize) },
            onSave = { data ->
                saveResponseRateData(data)
                refreshData?.invoke()
            },
            onDelete = { data ->
                deleteResponseRateData(data)
                refreshData?.invoke()
            },
            onAdd = {
                showAddDeviceDialog = true
            },
            // 将数据重载函数响应回来，可支持手动调用刷新信息
            onRefresh = { callback ->
                refreshData = callback // 将刷新回调函数保存到外部变量
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
                    DraggableAddDeviceDialogContent(
                        onClose = { showAddDeviceDialog = false },
                        refreshData = {
                            refreshData?.invoke() // 保存成功后刷新表格数据
                        }
                    )
                }
            }
        }
    }

    Text("这是问题库页面内容", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun VmEnvSetupContent() {
    Text("这是虚拟机环境配置页面内容", style = MaterialTheme.typography.bodyLarge)
}
