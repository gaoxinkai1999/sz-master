package com.example.sz.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Init;
import com.example.sz.Pojo.Team;
import com.example.sz.Sz_Component.Dispatch;
import com.example.sz.Sz_Component.Location;
import com.example.sz.Sz_Component.SendMessage;
import com.example.sz.Sz_Component.State_Check;
import com.example.sz.WebSocketServer;
import com.example.sz.World;
import com.example.sz.kami.kami_api;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    BeanFactory beanFactory;
    @Autowired
    Dispatch dispatch;
    @Autowired
    Init init;
    @Autowired
    Location location;
    @Autowired
    DmSoft dm;
    @Autowired
    State_Check check;
    @Autowired
    kami_api kami_api;
    @Autowired
    SendMessage sendMessage;
    @Autowired
    World world;

    @PostMapping("demo")
    public void demo(@RequestBody JSONObject json) {
        System.out.println(json);
        world.卡密= json.getString("卡密");
        world.teams= json.getJSONArray("teams").toJavaList(Team.class);
//
        System.out.println(world.teams);

        init.执行初始化();

        dispatch.run();




    }

    @Autowired
    WebSocketServer webSocketServer;

    @PostMapping("a")
    public void a(String text) {
        sendMessage.send(text);
    }


}
