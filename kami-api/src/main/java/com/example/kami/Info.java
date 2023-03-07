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
    public Date 到期时间;
    public int 有效期;

    public void 生成天卡() {
        this.有效期 = 24;
        生成卡密();

    }

    public void 生成测试卡() {
        this.有效期 = 1;
        生成卡密();

    }

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

    public void 生成到期时间() {
        到期时间 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(到期时间);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.HOUR, 有效期);
        // 这个时间就是日期往后推一天的结果
        到期时间 = calendar.getTime();
    }
}
