package com.xtl.ebusiness.common.questionAnswer.page

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
import com.xtl.ebusiness.pdd.responserate.funtion.*
import ui.theme.AppTheme

@Composable
fun QuestionAnswerScreen() {

    // 控制弹窗是否展示
    var showAddQuestionAnswerDialog by remember { mutableStateOf(false) }
    // 数据重新加载回调函数
    var refreshData by remember { mutableStateOf<(() -> Unit)?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        val tableHead = mapOf(
            "question" to TableHeadObj(description = "问题", width = 180.dp),
            "answer" to TableHeadObj(description = "答案", width = 200.dp),
        )

        val tableForm = mapOf(
            "question" to TableFormObj(type = FormFieldType.EditableText),
            "answer" to TableFormObj(type = FormFieldType.EditableText),
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
                showAddQuestionAnswerDialog = true
            },
            // 将数据重载函数响应回来，可支持手动调用刷新信息
            onRefresh = { callback ->
                refreshData = callback // 将刷新回调函数保存到外部变量
            }
        )

        if (showAddQuestionAnswerDialog) {
            val windowState = rememberWindowState(
                width = 450.dp,
                height = Dp.Unspecified
            )
            Window(
                onCloseRequest = { showAddQuestionAnswerDialog = false },
                title = "问题库",
                state = windowState
            ) {
                AppTheme {
                    DraggableAddDeviceDialogContent(
                        onClose = { showAddQuestionAnswerDialog = false },
                        refreshData = {
                            refreshData?.invoke() // 保存成功后刷新表格数据
                        }
                    )
                }
            }
        }
    }
}
