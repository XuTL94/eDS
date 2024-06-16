package com.xtl.automation.service;


import com.xtl.automation.entity.CommonResult;
import com.xtl.automation.properties.PddInfoProperties;
import com.xtl.automation.utils.AppiumUtils;
import com.xtl.automation.utils.SpringUtils;
import io.appium.java_client.android.AndroidDriver;

/**
 * 启动任务接口
 */
public abstract class StartTask {

    /**
     * 顾客启动聊天
     *
     * @return
     */
    public CommonResult<?> customerStartChartTask(String deviceID, String chatName) {
        return null;
    }

    /**
     * 取消任务
     * @param taskId
     * @return
     */
    public CommonResult<?> cancelChartTask(String taskId) {
        return null;
    }

    /**
     * 商家回复聊天
     *
     * @return
     */
    public CommonResult<?> businessStartChartTask(String deviceID, String chatName) {
        return null;
    }



    /**
     * 启动安卓应用(目前默认安卓配置)
     *
     * @param deviceID
     * @return
     */
    public AndroidDriver startApp(String deviceID) {
        String automationName = "UiAutomator2";
        String platformName = "Android";
        String appPackage = "com.xunmeng.pinduoduo";
        String appActivity = "com.xunmeng.pinduoduo.ui.activity.MainFrameActivity";
        return AppiumUtils.startApp(deviceID, automationName, platformName, appPackage, appActivity);
    }

    /**
     * 启动安卓应用(目前默认安卓配置)商家版（后续有时间改通用方式处理）
     *
     * @param deviceID
     * @return
     */
    public AndroidDriver startBusinessApp(String deviceID) {
        String automationName = "UiAutomator2";
        String platformName = "Android";
        String appPackage = "com.xunmeng.merchant";
        String appActivity = "com.xunmeng.merchant.ui.MainFrameTabActivity";
        return AppiumUtils.startApp(deviceID, automationName, platformName, appPackage, appActivity);
    }

}
