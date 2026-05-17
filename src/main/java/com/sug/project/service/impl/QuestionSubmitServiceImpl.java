package com.sug.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sug.project.common.ErrorCode;
import com.sug.project.exception.BusinessException;
import com.sug.project.model.entity.Question;
import com.sug.project.model.entity.QuestionSubmit;
import com.sug.project.service.QuestionSubmitService;
import com.sug.project.mapper.QuestionSubmitMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 钱晨
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2026-05-15 15:40:01
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Override
    public void validQuestionSubmit(QuestionSubmit questionSubmit, boolean isadd) {
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        Long questionid = questionSubmit.getQuestionId();
        String language= questionSubmit.getLanguage();
        String code = questionSubmit.getCode();


        // ------------------ 1. 新增时的必填项校验 ------------------
        if (isadd) {
            if (questionid == null || questionid <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目 id 不合法");
            }
            // 新增题目时，所有核心字段（包括判题用例和配置）均不能为空
            if (StringUtils.isAnyBlank(language,code)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目输入不完整（语言、代码均为必填项）");
            }
        }

        // ------------------ 2. 通用的字段长度规则校验（有值时才校验） ------------------
        if (StringUtils.isNotBlank(code) && code.length() > 8096) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标签总长度不能超过1024个字符");
        }



    }


}




