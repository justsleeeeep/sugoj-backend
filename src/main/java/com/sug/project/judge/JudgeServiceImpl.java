package com.sug.project.judge;

import cn.hutool.json.JSONUtil;
import com.sug.project.common.ErrorCode;
import com.sug.project.exception.BusinessException;
import com.sug.project.judge.codesandbox.CodeSandbox;
import com.sug.project.judge.codesandbox.CodeSandboxFactory;
import com.sug.project.judge.codesandbox.CodeSandboxProxy;
import com.sug.project.judge.codesandbox.model.ExecuteCodeRequest;
import com.sug.project.judge.codesandbox.model.ExecuteCodeResponse;
import com.sug.project.judge.strategy.DefaultJudgeStrategy;
import com.sug.project.judge.strategy.JudgeContext;
import com.sug.project.judge.strategy.JudgeStrategy;
import com.sug.project.model.dto.question.JudgeCase;
import com.sug.project.model.dto.question.JudgeConfig;
import com.sug.project.model.dto.questionsubmit.JudgeInfo;
import com.sug.project.model.entity.Question;
import com.sug.project.model.entity.QuestionSubmit;
import com.sug.project.model.enums.JudgeInfoMessageEnum;
import com.sug.project.model.enums.QuestionSubmitLanguageEnum;
import com.sug.project.model.enums.QuestionSubmitStatusEnum;
import com.sug.project.service.QuestionService;
import com.sug.project.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    QuestionSubmitService questionSubmitService;

    @Resource
    QuestionService questionService;

    @Resource
    JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;
    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        QuestionSubmit questionSubmit=questionSubmitService.getById(questionSubmitId);
        if(questionSubmit==null) throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        Long questionid=questionSubmit.getQuestionId();
        Question question=questionService.getById(questionid);
        if(question==null) throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        if(questionSubmit.getStatus().equals( QuestionSubmitStatusEnum.RUNNING.getValue()))
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题");
        }


        QuestionSubmit questionSubmitUpdateStatus= new QuestionSubmit();
        questionSubmitUpdateStatus.setId(questionSubmitId);
        questionSubmitUpdateStatus.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean updateStatus=questionSubmitService.updateById(questionSubmitUpdateStatus);
        if(!updateStatus)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目提交状态更新失败");
        }


        CodeSandbox codeSandbox= CodeSandboxFactory.newInstance(type);
        codeSandbox=new CodeSandboxProxy(codeSandbox);

        String code=questionSubmit.getCode();
        String language= questionSubmit.getLanguage();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList=judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest=ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setOutputList(executeCodeResponse.getOutputList());
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfoResponse =judgeManager.dojudge(judgeContext);

        QuestionSubmit questionSubmitUpdateJudgeInfo= new QuestionSubmit();
        questionSubmitUpdateJudgeInfo.setId(questionSubmitId);
        questionSubmitUpdateJudgeInfo.setJudgeInfo(judgeInfoResponse.toString());
        boolean updateJudgeInfo=questionSubmitService.updateById(questionSubmitUpdateJudgeInfo);
        if(!updateJudgeInfo)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目提交结果更新失败");
        }
        QuestionSubmit newQuestionSubmit=questionSubmitService.getById(questionSubmitId);
        return newQuestionSubmit;
    }
}
