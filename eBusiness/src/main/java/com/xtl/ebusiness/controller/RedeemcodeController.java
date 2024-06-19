package com.xtl.ebusiness.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xtl.ebusiness.entity.ResponseRateData;
import com.xtl.ebusiness.mapper.ResponseRateDataMapper;
import com.xtl.ebusiness.service.impl.ResponseRateDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 公共兑换码 (Api)
 *
 * @author xtl
 * @since 2024-01-16 13:31:43
 */
@RestController
@RequestMapping("/test")
public class RedeemcodeController {

    @Autowired
    ResponseRateDataServiceImpl responseRateDataServiceImpl;


    @GetMapping("/t")
    public void redeem() {
        Page<ResponseRateData> page = new Page<>(0, 10);

        Page<ResponseRateData> data = responseRateDataServiceImpl.page(page, null);

        Page<ResponseRateData> page2 = new Page<>(1, 10);
        Page<ResponseRateData> data2 = responseRateDataServiceImpl.page(page2, null);

        Page<ResponseRateData> page3 = new Page<>(1, 10);
        Page<ResponseRateData> data3 = responseRateDataServiceImpl.page(page3, null);

        // 使用lambdaQuery查询所有数据
        LambdaQueryChainWrapper<ResponseRateData> queryWrapper = responseRateDataServiceImpl.lambdaQuery();
        List<ResponseRateData> data4 = queryWrapper.list();
        System.out.println(data2);

    }


}
