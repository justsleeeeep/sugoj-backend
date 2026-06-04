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
        codeSandbox=new CodeSandboxProxy(codeSandbox);
        String code="import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "    public Main() {\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] var0) {\n" +
                "        Scanner var1 = new Scanner(System.in);\n" +
                "        int var2 = var1.nextInt();\n" +
                "        int var3 = var1.nextInt();\n" +
                "        System.out.println(\"结果 = \" + (var2 + var3));\n" +
                "    }\n" +
                "}";
        String language= QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String>inputList = Arrays.asList("1 2","2 5");
        ExecuteCodeRequest executeCodeRequest=ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }
}