package com.example.sz.Sz_Component;

import com.example.sz.Pojo.Team;
import com.example.sz.Pojo.Team_List;
import com.example.sz.Service.Service;
import com.example.sz.kami.kami_api;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环检测部队状态和分配任务
 */
@Component

public class Dispatch {
    @Autowired
    public Team_List team_list;
    @Autowired
    BeanFactory factory;

    @Autowired
    State_Check state_check;
    @Autowired
    kami_api kami_api;

    public void a() {
        while (true) {
            for (Team team : team_list.getTeams()) {
                if (!kami_api.a(team_list.get卡密())) {
                    return;
                }
                state_check.获取部队状态(team);
                System.out.println("部队当前状态为:" + team.state);
                switch (team.state) {
                    case "调动":
                    case "停留":
                    case "待命":
                        //该分支匹配到队伍正在待命，可以分配行动
                        分配行动(team);
                        break;
                    default:
                        //该分支匹配到队伍正在行动，不可分配任务

                }
            }
        }
    }

    public void 分配行动(Team team) {
        System.out.println(team);
        if (!team.task.是否完成) {
            System.out.println("开始执行    " + team.task.type);
            Service bean = factory.getBean(team.task.type, Service.class);
            bean.run(team);
        }

    }
}
