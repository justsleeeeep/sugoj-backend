package com.sug.project.judge;

import com.sug.project.judge.codesandbox.model.ExecuteCodeRequest;
import com.sug.project.judge.codesandbox.model.ExecuteCodeResponse;
import com.sug.project.model.dto.questionsubmit.JudgeInfo;
import com.sug.project.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {
     /**
      *
      * @param questionSubmitId
      * @return
      */
     QuestionSubmit doJudge(Long questionSubmitId);
}
