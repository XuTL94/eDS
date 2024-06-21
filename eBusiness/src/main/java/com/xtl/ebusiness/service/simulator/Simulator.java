package com.xtl.ebusiness.service.simulator;

import java.util.List;

public interface Simulator {

    /**
     * 通过adb查询当前已开启的模拟器设备ID  （但这种方式对于小白来说不方便）
     * @return
     */
    List<String> queryDeviceIdsByAdb();


    /**
     * 通过模拟器的名称查询设备ID（需指定模拟器类型，不同模拟器不同的执行命令，需要做适配）
     * @param simulatorType  模拟器类型  雷电  等等其他模拟器
     * @param simulatorName  模拟器里面的名称
     * @return
     */
    String queryDeviceIdBySimulatorName(int simulatorType,String simulatorName);

}
