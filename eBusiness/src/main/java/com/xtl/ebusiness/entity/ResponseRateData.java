package com.xtl.ebusiness.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("RESPONSE_RATE_DATA")
public class ResponseRateData {

    /**
     * 主键，自动递增
     */
    @TableId
    public Long id;

    /**
     * 类型，例如设备或聊天对象（0商家 1顾客）
     */
    public String type;

    /**
     * 设备ID
     */
    public String deviceId;

    /**
     * 聊天对象名称
     */
    public String chatName;

}
