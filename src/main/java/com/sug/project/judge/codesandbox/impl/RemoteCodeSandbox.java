package com.sug.project.judge.codesandbox.impl;

import com.sug.project.judge.codesandbox.CodeSandbox;
import com.sug.project.judge.codesandbox.model.ExecuteCodeRequest;
import com.sug.project.judge.codesandbox.model.ExecuteCodeResponse;

public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        System.out.println("Remote");
        return null;
    };
}
