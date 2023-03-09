package com.example.sz.kami;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.Sz_Component.SendMessage;
import com.example.sz.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kami {
    @Autowired
    kami_api kami_api;
    @Autowired
    World world;
    @Autowired
    SendMessage sendMessage;

    public void use(int 点数) {
        JSONObject use = kami_api.use(world.卡密, 点数);
        if (use.getIntValue("result") == 0) {
            //失败
            world.停止 = true;
        }

        sendMessage.send(use.getString("message"));
    }
}
