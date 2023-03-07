package com.example.sz.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Init;
import com.example.sz.Pojo.Team_List;
import com.example.sz.Sz_Component.Dispatch;
import com.example.sz.Sz_Component.Location;
import com.example.sz.Sz_Component.State_Check;
import com.example.sz.kami.kami_api;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
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
    Team_List team_list;
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

    @PostMapping("demo")
    public void demo(@RequestBody String json) {

        team_list = JSONObject.parseObject(json, Team_List.class);

        System.out.println(team_list);
        if (!kami_api.a(team_list.get卡密())) {
            return;
        }
        init.执行初始化();

        dispatch.a();

//        Point 获取队伍坐标 = location.获取队伍坐标(teams.get(0));
//        System.out.println(获取队伍坐标);


    }

    @PostMapping("a")
    public void a() {

        String property = System.getProperty("user.dir");
        System.out.println(property);
        Mat imread = Imgcodecs.imread(property + "/pic/xxx.png");
        System.out.println(imread);

    }


}
