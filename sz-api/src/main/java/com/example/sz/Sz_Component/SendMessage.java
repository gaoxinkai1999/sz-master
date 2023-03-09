package com.example.sz.Sz_Component;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SendMessage {
    @Autowired
    private WebSocketServer webSocketServer;

    public void send(String message) {
        if (WebSocketServer.session != null) {
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
            Date date = new Date();// 获取当前时间

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", message);
            jsonObject.put("date", sdf.format(date));
            webSocketServer.sendMessage(jsonObject.toJSONString());
        }
    }
}
