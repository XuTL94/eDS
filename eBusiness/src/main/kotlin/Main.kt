package com.xtl.ebusiness

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.springframework.boot.SpringApplication
import pdd.responserate.page.HomeScreen
import ui.theme.AppTheme



fun main() = application {

    // 启动 Spring Boot 应用程序
    SpringApplication.run(AutomationApplication::class.java)

    Window(onCloseRequest = ::exitApplication) {
        AppTheme {
            ToastUtils.ToastMessage()
            HomeScreen()
        }
    }
}

