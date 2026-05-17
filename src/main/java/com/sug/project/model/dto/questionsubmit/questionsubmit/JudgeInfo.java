package com.sug.project.model.dto.questionsubmit.questionsubmit;
import lombok.Data;

@Data
public class JudgeInfo {
    /*
    * 程序执行信息
    * */
    private String message;
    /*
     * 时间ms
     * */
    private Long time;
    /*
     * 空间kb
     * */
    private Long memory;
}
