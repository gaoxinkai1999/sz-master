package com.example.kami;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.kami.Result.失败;

@SpringBootTest
class KaMiApplicationTests {
    @Autowired
    Info info;
    @Autowired
    Dao dao;
    @Autowired
    Result result;
    @Test
    void contextLoads() {
        String 卡密="demo";
        int 扣减点数=1;


    }

}
