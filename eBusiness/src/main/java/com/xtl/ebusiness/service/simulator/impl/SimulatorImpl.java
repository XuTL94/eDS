package com.xtl.ebusiness.service.simulator.impl;

import com.xtl.ebusiness.mapper.CommonMapper;
import com.xtl.ebusiness.service.simulator.Simulator;
import com.xtl.ecore.exception.BusinessException;
import com.xtl.ecore.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimulatorImpl implements Simulator {

    @Autowired
    CommonMapper commonMapper;

    @Override
    public List<String> queryDeviceIdsByAdb() {
        List<String> deviceIds = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ADB_PATH", "devices");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device")) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 0) {
                        deviceIds.add(parts[0]);
                    }
                }
            }
        } catch (Exception e) {
            log.error("adb查询设备失败:{}", e.getMessage(), e);
        }
        return deviceIds;
    }

    @Override
    public String queryDeviceIdBySimulatorName(int simulatorType, String simulatorName) {

        String simulatorPath = commonMapper.querySimulatorPathByType(simulatorType)+ "\\adb.exe";
        if(StringUtils.isEmpty(simulatorPath)){
            throw new BusinessException("模拟器路径未配置");
        }

        String deviceId = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(simulatorPath, "adb", "--name", simulatorName, "--command", "devices");
            processBuilder.redirectErrorStream(true); // 合并标准错误流到标准输出流
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device")) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 0) {
                        deviceId = parts[0];
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.error("模拟器自带的adb查询设备失败 simulatorType：{}   simulatorName：{}  msg:{} ", simulatorType, simulatorName, e.getMessage(), e);
            throw new BusinessException("模拟器自带的adb查询设备失败");
        }
        return deviceId;
    }
}
