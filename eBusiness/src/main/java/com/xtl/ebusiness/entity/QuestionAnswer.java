package com.xtl.ebusiness.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("QUESTION_ANSWER")
public class QuestionAnswer {

    @Id
    private String question; // 问题
    private String answer; // 答案

}
