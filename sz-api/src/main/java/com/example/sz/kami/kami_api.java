package com.example.sz.kami;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import org.springframework.stereotype.Component;

@Component
public interface kami_api {
    @Post("http://47.243.129.90:8081/use")
    boolean a(@Body("卡密") String 卡密);
}
