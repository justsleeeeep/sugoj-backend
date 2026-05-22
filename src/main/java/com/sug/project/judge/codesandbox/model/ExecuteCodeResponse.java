package com.sug.project.judge.codesandbox.model;

import com.sug.project.model.dto.questionsubmit.JudgeInfo;
import lombok.Data;

import java.util.List;
@Data
public class ExecuteCodeResponse
{
    /**
     * 输出结果
     */
    private List<String> output;
    /**
     * 接口信息
     */
    private String message;
    /**
     * 接口状态
     */
    private Integer status;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
