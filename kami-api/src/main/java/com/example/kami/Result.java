package com.example.kami;

import org.springframework.stereotype.Component;

@Component
public class Result {

    public static final int 成功=1;
    public static final int 失败=0;
    public int result;
    public Object message;
}
