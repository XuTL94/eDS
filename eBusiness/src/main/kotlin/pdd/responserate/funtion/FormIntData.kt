package com.xtl.ebusiness.pdd.responserate.funtion

import FormPageDataResult
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.xtl.ebusiness.entity.ResponseRateData
import com.xtl.ebusiness.service.ResponseRateDataService
import com.xtl.ebusiness.service.replyRateBooster.impl.PddReplyRateBooster
import com.xtl.ebusiness.service.simulator.Simulator
import com.xtl.ecore.utils.SpringUtils
import pdd.responserate.data.ResponseRateDataKot

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
            simulatorType = responseRateData.simulatorType,
            simulatorName = responseRateData.simulatorName,
            roleType = responseRateData.roleType,
            chatName = responseRateData.chatName
        )
    }

    return FormPageDataResult(count = pageResult.total.toInt(), data = data)
}

/**
 * 新增数据
 */
fun addResponseRateData(data : ResponseRateData): Boolean {

    var responseRateDataService = SpringUtils.getBean(ResponseRateDataService::class.java)

    return try {
        responseRateDataService.save(data)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

/**
 * 表格数据保存
 */
fun saveResponseRateData(data: List<Any>) {
    val responseRateDataService = SpringUtils.getBean(ResponseRateDataService::class.java)

    // Any类型数据转换为 ResponseRateDataKot
    val responseRateDataList = data.filterIsInstance<ResponseRateDataKot>().map { responseRateDataKot ->
        ResponseRateData().apply {
            this.id = responseRateDataKot.id
            this.simulatorType = responseRateDataKot.simulatorType
            this.simulatorName = responseRateDataKot.simulatorName
            this.roleType = responseRateDataKot.roleType
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

fun startTask(data : ResponseRateDataKot){

    val kimiSystem = "我是客服助手，是电商卖家，将我发你的语句转换成其他相同意义，但是语句不一样，简洁优雅，带表情。"

    val simulator = SpringUtils.getBean(Simulator::class.java)
    val pddReplyRateBooster = SpringUtils.getBean(PddReplyRateBooster::class.java)

    try {
        // 通过名称查询设备ID
        val deviceID = simulator.queryDeviceIdBySimulatorName(data.simulatorType, data.simulatorName)
        // 根据设备ID拉取APP
        pddReplyRateBooster.startReplyRateTask(data.roleType, kimiSystem, deviceID, data.chatName)
        ToastUtils.success("启动成功")
    } catch (e: Exception) {
        e.printStackTrace()
        ToastUtils.error(e.message ?: "未知错误")
    }

}