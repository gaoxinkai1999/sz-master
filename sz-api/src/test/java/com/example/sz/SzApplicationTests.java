package com.example.sz;

import com.example.sz.Controller.Controller;
import com.example.sz.Cv.Cv;
import com.example.sz.Dm.DmConfig;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team_List;
import com.example.sz.Service.daye.Daye;
import com.example.sz.Service.lianji.Lianji;
import com.example.sz.Sz_Component.Action;
import com.example.sz.Sz_Component.Dispatch;
import com.example.sz.Sz_Component.Find_Obj;
import com.example.sz.Sz_Component.ZhengBing;
import com.example.sz.kami.kami_api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SzApplicationTests {
    @Autowired
    Controller controller;

    @Autowired
    Cv cv;
    @Autowired
    DmConfig config;
    @Value("${license}")
    String a;
    @Autowired
    Find_Obj find;
    @Autowired
    Action action;
    @Autowired
    Team_List team_list;
    @Autowired
    DmSoft dm;
    @Autowired
    Daye demo;
    @Autowired
    ZhengBing zhengbing;
    @Autowired
    Dispatch 调度;
    @Autowired
    Lianji lianji;
    @Autowired
    kami_api kami_api;

    @Test
    void contextLoads() {
//        String a="{\"圈数\":1,\"是否主城征兵\":false,\"调动点\":[{\"x\":629,\"y\":909}],\"调用点信息s\":[{\"point\":{\"x\":629,\"y\":909},\"是否失效\":false,\"矩阵\":[{\"point\":{\"x\":639,\"y\":909},\"是否失效\":true},{\"point\":{\"x\":634,\"y\":914},\"是否失效\":false},{\"point\":{\"x\":629,\"y\":919},\"是否失效\":false},{\"point\":{\"x\":624,\"y\":914},\"是否失效\":false},{\"point\":{\"x\":619,\"y\":909},\"是否失效\":false},{\"point\":{\"x\":624,\"y\":904},\"是否失效\":false},{\"point\":{\"x\":629,\"y\":899},\"是否失效\":false},{\"point\":{\"x\":634,\"y\":904},\"是否失效\":false}]}],\"阈值\":0.0}";
//        Daye_Config daye_config = JSONObject.parseObject(a, Daye_Config.class);
//        System.out.println(daye_config);
//        boolean b = kami_api.a("TwSHde4QMjPXcEj6");
//        System.out.println(b);

    }


}
