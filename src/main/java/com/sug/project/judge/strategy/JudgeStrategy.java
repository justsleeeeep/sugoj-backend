package com.sug.project.judge.strategy;

import com.sug.project.judge.codesandbox.model.JudgeInfo;

public interface JudgeStrategy {
    /**
     * 判题策略
     * @param judgeContext
     * @return
     */
    JudgeInfo dojudge(JudgeContext judgeContext);
}
