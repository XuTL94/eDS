package com.xtl.automation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtl.automation.entity.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionAnswerService extends IService<QuestionAnswer> {


    /**
     * 获取随机问题(排除当前问题)
     * @param question 问题
     * @return
     */
    QuestionAnswer getQuestionAnswer(String question);

}