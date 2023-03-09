package com.example.kami;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

@Component
@ToString
public class Info {
    public String 卡密;
    public int 剩余点数;
    public int 累计点数;


    private void 生成卡密() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        卡密 = stringBuffer.toString();
    }


}
