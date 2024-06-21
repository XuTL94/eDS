package pdd.responserate.data

import FormPageDataResult
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.xtl.ebusiness.entity.ResponseRateData
import com.xtl.ebusiness.service.ResponseRateDataService
import com.xtl.ecore.utils.SpringUtils

/**
 * 设备数据类
 */
data class ResponseRateDataKot(
    val id: String,
    val simulatorType : Int, // 模拟器类型  0雷电 1闪现 2机电 等等
    val simulatorName : String, // 模拟器启动名称
    val roleType: Int,  // 类型：商家、用户 0商家 1顾客
    val chatName: String, // 聊天对象名称
    val switch: Boolean = false // 启动 关闭
)

// 类型枚举
val ResponseRateTypeOptions = listOf(
    "0" to "商家",
    "1" to "顾客"
)

val SimulatorTypeOptions = listOf(
    "0" to "雷电"
)
