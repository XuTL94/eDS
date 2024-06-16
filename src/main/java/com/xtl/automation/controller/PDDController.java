package com.xtl.automation.controller;


import com.xtl.automation.entity.CommonResult;
import com.xtl.automation.sdk.kimi.api.KimiApi;
import com.xtl.automation.service.impl.PDDStartTaskImpl;
import com.xtl.automation.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PDDController {

    @Autowired
    PDDStartTaskImpl pDDStartTaskImpl;

    @GetMapping("/customerStartChartTask")
    public CommonResult<?> customerStartChartTask(String deviceID, String chatName) {
        return pDDStartTaskImpl.customerStartChartTask(deviceID,chatName);
    }


    @GetMapping("/businessStartChartTask")
    public CommonResult<?> businessStartChartTask(String deviceID, String chatName) {
        return pDDStartTaskImpl.businessStartChartTask(deviceID,chatName);
    }

    @GetMapping("/cancelChartTask")
    public CommonResult<?> cancelChartTask(String taskId) {
        return pDDStartTaskImpl.cancelChartTask(taskId);
    }


}
