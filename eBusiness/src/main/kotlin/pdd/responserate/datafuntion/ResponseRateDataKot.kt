package pdd.responserate.datafuntion

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
    val type: String,  // 类型：商家、用户
    val deviceId: String, // 设备ID
    val chatName: String, // 聊天对象名称
    val switch: Boolean = false // 启动 关闭
)

// 类型枚举
val ResponseRateTypeOptions = listOf(
    "0" to "商家",
    "1" to "顾客"
)


/**
 * 表格数据加载
 */
fun loadResponseRateData(page: Long, pageSize: Long): FormPageDataResult<ResponseRateDataKot> {
    val responseRateDataService = SpringUtils.getBean(ResponseRateDataService::class.java)
    val pageRequest = Page<ResponseRateData>(page, pageSize)
    val pageResult: Page<ResponseRateData> = responseRateDataService.page(pageRequest)

    val data = pageResult.records.map { responseRateData ->
        ResponseRateDataKot(
            id = responseRateData.id.toString(),
            type = responseRateData.type,
            deviceId = responseRateData.deviceId,
            chatName = responseRateData.chatName
        )
    }
    return FormPageDataResult(count = pageResult.total.toInt(), data = data)
}


/**
 * 表格数据保存
 */
fun saveResponseRateData(data: List<Any>) {
    val responseRateDataService = SpringUtils.getBean(ResponseRateDataService::class.java)

    //  Any类型数据转换为 ResponseRateDataKot
    val responseRateDataList = data.filterIsInstance<ResponseRateDataKot>().map { responseRateDataKot ->
        ResponseRateData().apply {
            this.id = responseRateDataKot.id
            this.type = responseRateDataKot.type
            this.deviceId = responseRateDataKot.deviceId
            this.chatName = responseRateDataKot.chatName
        }
    }
    responseRateDataService.saveOrUpdateBatch(responseRateDataList)
}

/**
 * 表格数据保存
 */
fun deleteResponseRateData(data: List<Any>) {
    val responseRateDataService = SpringUtils.getBean(ResponseRateDataService::class.java)
    val idsToDelete = data.filterIsInstance<ResponseRateDataKot>().map { it.id }
    responseRateDataService.removeByIds(idsToDelete)
}