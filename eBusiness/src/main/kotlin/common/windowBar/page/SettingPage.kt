package com.xtl.ebusiness.common.windowBar.page

import TableFormObj
import TableHeadObj
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
import com.xtl.ebusiness.setting.questionAnswer.page.QuestionAnswerScreen
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
            .background(MaterialTheme.colorScheme.surface)
    ) {
        when (selectedItem) {
            NavigationItem.QUESTION_BANK -> QuestionAnswerScreen()
            NavigationItem.VM_ENV_SETUP -> VmEnvSetupContent()
        }
    }
}

@Composable
fun VmEnvSetupContent() {
    Text("这是虚拟机环境配置页面内容", style = MaterialTheme.typography.bodyLarge)
}
