package com.sug.project.judge;

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
