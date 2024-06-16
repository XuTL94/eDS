
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.xtl.automation.AutomationApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.boot.SpringApplication
import pdd.responserate.page.HomeScreen
import ui.theme.AppTheme



fun main() = application {

    // 启动Spring Boot应用
    GlobalScope.launch(Dispatchers.Default) {
        SpringApplication.run(AutomationApplication::class.java)
    }


    Window(onCloseRequest = ::exitApplication) {
        AppTheme {
            HomeScreen()
        }
    }
}

