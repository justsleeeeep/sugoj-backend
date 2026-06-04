package com.sug.project.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.sug.project.judge.codesandbox.CodeSandbox;
import com.sug.project.judge.codesandbox.model.ExecuteCodeRequest;
import com.sug.project.judge.codesandbox.model.ExecuteCodeResponse;

public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        System.out.println("Remote");
        String jsonStr= JSONUtil.toJsonStr(executeCodeRequest);
        String url="192.168.40.135:7528/executeCode";
        String resultStr = HttpUtil.createPost(url)
                .body(jsonStr)
                .execute()
                .body();
        return JSONUtil.toBean(resultStr,ExecuteCodeResponse.class);
    };
}
