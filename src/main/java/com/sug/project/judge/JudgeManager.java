package com.sug.project.judge;

import com.sug.project.judge.strategy.DefaultJudgeStrategy;
import com.sug.project.judge.strategy.JavaLanguageJudgeStrategy;
import com.sug.project.judge.strategy.JudgeContext;
import com.sug.project.judge.strategy.JudgeStrategy;
import com.sug.project.model.dto.questionsubmit.JudgeInfo;
import com.sug.project.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

@Service
public class JudgeManager {
    /**
     *
     * @return
     */
    JudgeInfo dojudge(JudgeContext judgeContext)
    {
        QuestionSubmit questionSubmit=judgeContext.getQuestionSubmit();
        JudgeStrategy judgeStrategy=new DefaultJudgeStrategy();
        if(questionSubmit.getLanguage().equals("java"))
        {
            judgeStrategy=new JavaLanguageJudgeStrategy();
        }
        JudgeInfo judgeInfo =judgeStrategy.dojudge(judgeContext);
        return judgeInfo;
    }
}
