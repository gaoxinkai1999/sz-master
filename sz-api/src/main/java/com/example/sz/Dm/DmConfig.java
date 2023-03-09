package com.example.sz.Dm;

import com.example.sz.Sz_Component.SendMessage;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data

public class DmConfig {

    @Autowired
    SendMessage sendMessage;
    private static String license = "6428881518c263e745b0f01e374c8cdb9936a6";
    /**
     * 大漠插件执行组件
     */
    private final ActiveXComponent dm = new ActiveXComponent("dm.dmsoft");




    public void version() {
        System.out.println("大漠插件版本：" + dm.invoke("Ver").getString());
    }


    public void register() {
        int success = Dispatch.call(dm, "Reg", license, "").getInt();
        sendMessage.send("注册状态码:"+success);
        sendMessage.send((success == 1 ? "注册成功" : "注册失败"));
    }


    public ActiveXComponent getDm() {
        return dm;
    }


}
