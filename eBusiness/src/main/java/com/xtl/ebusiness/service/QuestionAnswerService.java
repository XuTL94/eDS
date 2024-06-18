package com.xtl.ebusiness.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtl.ebusiness.entity.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;

public interface QuestionAnswerService extends IService<QuestionAnswer> {


    /**
     * 获取随机问题(排除当前问题)
     * @param question 问题
     * @return
     */
    QuestionAnswer getQuestionAnswer(String question);

}