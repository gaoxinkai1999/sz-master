package com.example.sz.baidu;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ai {
    private final String api_key = "fUlEjCvsoaQ6hzFlBkcUhwij";
    private final String secret_key = "tkV6tWwra6k4WyLYBX2I0H87bBSr6ZmM";
    @Autowired
    Api api;

    public JSONObject start(String path) {

        // 调用Forest请求接口，并获取响应返回结果
        JSONObject result = api.token(api_key, secret_key);
        String access_token = result.getString("access_token");
        String base64 = new Base64().ImgToBase64(path);
        return api.ai(base64, 0.8, access_token);

    }
}
