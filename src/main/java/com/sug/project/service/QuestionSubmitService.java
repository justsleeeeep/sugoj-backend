package com.sug.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sug.project.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.sug.project.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 钱晨
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2026-05-15 15:40:01
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    void validQuestionSubmit(QuestionSubmit questionSubmit, boolean isadd);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

}
