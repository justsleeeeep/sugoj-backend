package com.sug.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sug.project.common.ErrorCode;
import com.sug.project.exception.BusinessException;
import com.sug.project.model.entity.Question;
import com.sug.project.service.QuestionService;
import com.sug.project.mapper.QuestionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 钱晨
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2026-05-15 15:37:50
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {
    @Override
    public void validQuestion(Question question, boolean isadd) {
        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        String title = question.getTitle();
        String content = question.getContent();
        String tags = question.getTags();
        String answer = question.getAnswer();
        String judgeCase = question.getJudgeCase();
        String judgeConfig = question.getJudgeConfig();

        // ------------------ 1. 新增时的必填项校验 ------------------
        if (isadd) {
            // 新增题目时，所有核心字段（包括判题用例和配置）均不能为空
            if (StringUtils.isAnyBlank(title, content, tags, answer, judgeCase, judgeConfig)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目输入不完整（标题、内容、标签、答案、判题用例、判题配置均为必填项）");
            }
        }

        // ------------------ 2. 通用的字段长度规则校验（有值时才校验） ------------------
        // 校验标题和标签长度（防止超出数据库 varchar 的限制）
        if (StringUtils.isNotBlank(title) && title.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长，不能超过512个字符");
        }
        if (StringUtils.isNotBlank(tags) && tags.length() > 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标签总长度不能超过1024个字符");
        }

        // 校验大文本长度（对应数据库 text，设定合理的安全阈值，防止恶意超大文本引发内存溢出）
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长，不能超过8192个字符");
        }
        if (StringUtils.isNotBlank(answer) && answer.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "答案过长，不能超过8192个字符");
        }


    }
}




