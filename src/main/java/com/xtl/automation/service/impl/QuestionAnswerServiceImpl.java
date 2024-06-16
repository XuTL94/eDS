package com.xtl.automation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtl.automation.entity.QuestionAnswer;
import com.xtl.automation.mapper.QuestionAnswerMapper;
import com.xtl.automation.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements QuestionAnswerService {

    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;

    @Override
    public QuestionAnswer getQuestionAnswer(String question) {
        // 查询所有与传入问题不同，且类型为0或等于传参type的问题
        List<QuestionAnswer> questionAnswers = questionAnswerMapper.selectList(
                new QueryWrapper<QuestionAnswer>()
                        .ne("question", question)
        );

        if (questionAnswers.isEmpty()) {
            return null; // 如果没有找到符合条件的问题，返回null或其他适当的值
        }

        // 返回第一个符合条件的问题
        return questionAnswers.get(0);
    }
}
