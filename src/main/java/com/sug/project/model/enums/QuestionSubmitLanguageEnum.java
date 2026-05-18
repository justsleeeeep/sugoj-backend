package com.sug.project.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目提交语言枚举
 */
public enum QuestionSubmitLanguageEnum {

    JAVA("java","java"),
    CPLUSPLUS("c++","c++"),
    GOLANG("golang","golang");
    /**
     * 文本
     */
    private final String text;

    /**
     * 值
     */
    private final String value;

    QuestionSubmitLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    /**
     * 获取值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values())
                .map(item -> item.value)
                .collect(Collectors.toList());
    }
}