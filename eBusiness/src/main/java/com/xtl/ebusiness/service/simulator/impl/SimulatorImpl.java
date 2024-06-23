package com.xtl.ebusiness.service.simulator.impl;

import com.xtl.ebusiness.mapper.CommonMapper;
import com.xtl.ebusiness.service.simulator.Simulator;
import com.xtl.ecore.exception.BusinessException;
import com.xtl.ecore.utils.CmdUtils;
import com.xtl.ecore.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class SimulatorImpl implements Simulator {

    @Autowired
    CommonMapper commonMapper;


    @Override
    public String startSimulatorByDeviceName(int simulatorType, String deviceName) throws BusinessException {

        String path = commonMapper.querySimulatorPathByType(simulatorType);
        if (StringUtils.isEmpty(path)) {
            throw new BusinessException("模拟器路径未配置" );
        }

        try {
            String ldconsolePath = Paths.get(path, "ldconsole.exe").toString();
            String adbPath = Paths.get(path, "adb.exe").toString();

            // 检查设备是否已运行，如果未运行，则自动拉起
            if (!isDeviceRunning(ldconsolePath, deviceName)) {
                CmdUtils.executeCommand(ldconsolePath, "launch", "--name", deviceName);
            }

            // 解锁屏幕
            CmdUtils.executeCommand(ldconsolePath, "adb", "--name", deviceName, "--command", "shell input keyevent 82");

            // 查询当前模拟器设备ID
            String deviceId = getDeviceIdByName(ldconsolePath, deviceName);


            return deviceId;


        } catch (Exception e) {
            log.error("启动模拟器设备失败 simulatorType：{} deviceName：{} msg:{} ", simulatorType, deviceName, e.getMessage(), e);
            throw new BusinessException("启动模拟器设备失败");
        }
    }

    @Override
    public String getDeviceIdByName(String ldconsolePath, String deviceName) {

        try {
            String output = CmdUtils.executeCommand(ldconsolePath, "list2");
            List<String> lines = Arrays.asList(output.split("\n"));
            for (String line : lines) {
                String[] fields = line.split(",");
                if (fields.length > 1 && deviceName.equals(fields[1].trim())) {
                    String deviceId = fields[0].trim();
                    int port = 5554 + Integer.parseInt(deviceId) * 2;
                    return "emulator-" + port;
                }
            }
        } catch (Exception e) {
            log.error("查询模拟器设备ID或计算端口号失败 deviceName：{} msg:{} ", deviceName, e.getMessage(), e);
            throw new BusinessException("查询模拟器设备ID或计算端口号失败");
        }
        return null;
    }


    private boolean isDeviceRunning(String simulatorPath, String deviceName){
        String output = CmdUtils.executeCommand(simulatorPath, "isrunning", "--name", deviceName);
        return "running".equalsIgnoreCase(output.trim());
    }


}
