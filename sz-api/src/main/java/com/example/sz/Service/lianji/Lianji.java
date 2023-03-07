package com.example.sz.Service.lianji;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.Pojo.Team;
import com.example.sz.Service.Service;
import com.example.sz.Sz_Component.Action;
import com.example.sz.Sz_Component.Find_Obj;
import com.example.sz.Sz_Component.ZhengBing;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.sz.pic_info.扫荡;

@org.springframework.stereotype.Service

public class Lianji implements Service {
    @Autowired
    Action action;
    @Autowired
    Find_Obj find;
    @Autowired
    ZhengBing zhengbing;

    @Override
    public void run(Team team) {
        if (zhengbing.识别兵力(team)) {
            System.out.println("需要征兵");
            zhengbing.主城征兵(team);
        }
        Lianji_Config lianji_info = JSONObject.parseObject(team.task.配置JSON, Lianji_Config.class);
        find.a(lianji_info.练级地点);
        action.run(team, 扫荡);
    }
}
