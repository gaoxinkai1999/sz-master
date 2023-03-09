package com.example.sz.kami;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import org.springframework.stereotype.Component;

@Component
public interface kami_api {
    @Post("http://localhost:8090/use")
    JSONObject use(@Body("卡密") String 卡密,@Body("点数") int 点数);
}
