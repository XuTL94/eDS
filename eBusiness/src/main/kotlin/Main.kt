package com.xtl.ebusiness


import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
import com.xtl.ebusiness.common.windowBar.page.WindowBar
import org.springframework.boot.SpringApplication
import pdd.responserate.page.HomeScreen
import ui.theme.AppTheme

fun main() = application {

    // 启动 Spring Boot 应用程序
    SpringApplication.run(AutomationApplication::class.java)


    Window(
        onCloseRequest = ::exitApplication,
        title = "eDS",
        icon = painterResource("icons/icon.png"),
        resizable = false,
        undecorated = true
    ) {
        AppTheme {
            // 自定义窗口状态栏
            WindowDraggableArea {
                WindowBar(
                    onMinimize = { window.isMinimized = true },
                    onClose = { exitApplication() }
                )
            }

            // 主界面内容
            ToastUtils.ToastMessage()
            LoadingUtils.LoadingDialog()
            HomeScreen()
        }

    }
}