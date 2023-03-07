package com.example.sz.baidu;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class Base64 {

    public String ImgToBase64(String imgFile) {

        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
