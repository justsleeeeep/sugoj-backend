package com.sug.project.judge.codesandbox.model;

import lombok.Data;

import java.util.List;
@Data
public class ExecuteCodeRequest {
    /**
     * 输入用例
     */
    private List<String> input;
    /**
     * 代码
     */
    private String code;
    /**
     *编程语言
     */
    private String language;
}
