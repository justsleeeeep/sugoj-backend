package com.sug.project.judge.strategy;

import com.sug.project.model.dto.questionsubmit.JudgeInfo;
import com.sug.project.model.entity.Question;
import com.sug.project.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class JudgeContext {

    List<String> outputList;
    JudgeInfo judgeInfo;
    Question question;
    QuestionSubmit questionSubmit;
}
