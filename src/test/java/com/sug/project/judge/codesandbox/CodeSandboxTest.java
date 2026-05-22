package com.sug.project.judge.codesandbox;

import com.sug.project.judge.codesandbox.impl.ExampleCodeSandbox;
import com.sug.project.judge.codesandbox.impl.RemoteCodeSandbox;
import com.sug.project.judge.codesandbox.model.ExecuteCodeRequest;
import com.sug.project.judge.codesandbox.model.ExecuteCodeResponse;
import com.sug.project.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CodeSandboxTest {

    @Value("${codesandbox.type:example}")
    private String type;
    @Test
    void executeCode() {
        CodeSandbox codeSandbox=CodeSandboxFactory.newInstance(type);
        String code="int main(){}";
        String language= QuestionSubmitLanguageEnum.CPLUSPLUS.getValue();
        List<String>inputList = Arrays.asList("1 2","3,4");
        ExecuteCodeRequest executeCodeRequest=ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .input(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
    }
}