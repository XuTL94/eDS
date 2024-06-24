package com.xtl.ebusiness.common.windowBar.page

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import icons.Minimize
import ui.theme.AppTheme

@Composable
fun WindowBar(
    onMinimize: () -> Unit,
    onClose: () -> Unit
) {

    // 设置窗口控制
    var showSettings by remember { mutableStateOf(false) }
    if (showSettings) {
        val windowState = rememberWindowState(
/*            width = 650.dp,
            height = 500.dp,*/
            position = WindowPosition.Aligned(Alignment.Center)
        )
        Window(
            onCloseRequest = { showSettings = false },
            title = "",
            icon = null,
            state = windowState
        ) {
            AppTheme {
                SettingPage()
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .height(35.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f)) // 占据剩余空间以将按钮放在右侧

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "MoreVert",
            modifier = Modifier
                .clickable { showSettings = true }
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )


        Icon(
            imageVector = Icons.Default.Minimize,
            contentDescription = "Minimize",
            modifier = Modifier
                .clickable(onClick = onMinimize)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            modifier = Modifier
                .clickable(onClick = onClose)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

