package com.example.sz.baidu;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ocr {
    private final String api_key = "IPoOqSSrfaig3PfqaLC2VwGG";
    private final String secret_key = "OEpvb933WiX0UEfaBLpPz0hGUnI2nAgR";

    @Autowired
    Api api;

    public JSONObject start(String path) {

        // 调用Forest请求接口，并获取响应返回结果
        JSONObject result = api.token(api_key, secret_key);
        String access_token = result.getString("access_token");
        String base64 = new Base64().ImgToBase64(path);
        return api.ocr(access_token, base64);

    }
}
