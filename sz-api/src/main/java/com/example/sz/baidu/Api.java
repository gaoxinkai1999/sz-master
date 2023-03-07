package com.example.sz.baidu;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.*;
import org.springframework.stereotype.Component;

@Component
public interface Api {
    //Token
    @Post("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id={api_key}&client_secret={secret_key}")
    JSONObject token(@Var("api_key") String api_key, @Var("secret_key") String secret_key);


    @Post("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/detection/9jidi")
    JSONObject ai(@JSONBody("image") String base64, @JSONBody("threshold") double threshold, @Query("access_token") String token);

    //ocr
    @Post("https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token={token}")
    JSONObject ocr(@Var("token") String token, @Body("image") String base64);

}
