package com.sug.project.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sug.project.annotation.AuthCheck;
import com.sug.project.common.BaseResponse;
import com.sug.project.common.DeleteRequest;
import com.sug.project.common.ErrorCode;
import com.sug.project.common.ResultUtils;
import com.sug.project.constant.CommonConstant;
import com.sug.project.exception.BusinessException;
import com.sug.project.model.dto.post.PostAddRequest;
import com.sug.project.model.dto.post.PostQueryRequest;
import com.sug.project.model.dto.post.PostUpdateRequest;
import com.sug.project.model.dto.question.QuestionAddRequest;
import com.sug.project.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.sug.project.model.entity.Post;
import com.sug.project.model.entity.Question;
import com.sug.project.model.entity.QuestionSubmit;
import com.sug.project.model.entity.User;
import com.sug.project.service.PostService;
import com.sug.project.service.QuestionService;
import com.sug.project.service.QuestionSubmitService;
import com.sug.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    /**
     * 创建
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addQuestion(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest, HttpServletRequest request) {
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
        long newQuestionSubmitId = questionSubmit.getId();
        return ResultUtils.success(newQuestionSubmitId);
    }


}
