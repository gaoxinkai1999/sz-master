package com.example.sz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    public String date ;
    public String text;
    public Message(String text){
        this.text=text;
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        this.date=sdf.format(date);
    }
}
