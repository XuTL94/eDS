package pdd.responserate.data

import FormPageDataResult

/**
 * 设备数据类
 */
data class ResponseRateData(
    val type: String,  // 类型：商家、用户
    val deviceId: String, // 设备ID
    val chatName: String, // 聊天对象名称
    val switch: Boolean = false // 启动 关闭
)

// 类型枚举
val ResponseRateTypeOptions = listOf(
    "1" to "商家",
    "2" to "用户"
)


/**
 * 模拟数据加载函数
 */
fun loadResponseRateData(page: Int, pageSize: Int): FormPageDataResult<ResponseRateData> {
    val data = List(pageSize) { index ->
        ResponseRateData(
            type = if (index % 2 == 0) "1" else "2",
            deviceId = "设备ID-$index",
            chatName = "聊天对象名称-$index"
        )
    }
    return FormPageDataResult(count = 100, data = data)
}