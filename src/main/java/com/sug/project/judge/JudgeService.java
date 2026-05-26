package com.sug.project.judge;

import com.sug.project.judge.codesandbox.model.ExecuteCodeRequest;
import com.sug.project.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 判题服务
 */
public interface JudgeService {
     Integer doJudge(Long questionSubmitId);
}
