package com.sug.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sug.project.common.BaseResponse;
import com.sug.project.common.ErrorCode;
import com.sug.project.common.ResultUtils;
import com.sug.project.exception.BusinessException;
import com.sug.project.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.sug.project.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.sug.project.model.entity.QuestionSubmit;
import com.sug.project.model.entity.User;
import com.sug.project.service.QuestionService;
import com.sug.project.service.QuestionSubmitService;
import com.sug.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 问题接口
 *
 * @author sug
 */
@RestController
@RequestMapping("/questionsubmit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    @Resource QuestionService questionService;
    /**
     * 创建
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest, HttpServletRequest request) {
        if (questionSubmitAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitAddRequest, questionSubmit);
        // 校验
        questionSubmitService.validQuestionSubmit(questionSubmit, true);
        User loginUser = userService.getLoginUser(request);
        questionSubmit.setUserId(loginUser.getId());
        boolean result = questionSubmitService.save(questionSubmit);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        questionService.incrementSubmitNum(questionId);
        long newQuestionSubmitId = questionSubmit.getId();
        return ResultUtils.success(newQuestionSubmitId);
    }

    /**
     * 分页获取题目提交列表
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<QuestionSubmit>> listQuestionSubmitByPage(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        if (questionSubmitQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(
                new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        return ResultUtils.success(questionSubmitPage);
    }

}
