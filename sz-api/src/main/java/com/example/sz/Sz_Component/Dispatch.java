package com.example.sz.Sz_Component;

import com.example.sz.Pojo.Team;
import com.example.sz.Service.daye.Daye;
import com.example.sz.Service.lianji.Lianji;
import com.example.sz.World;
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
    World world;
    @Autowired
    BeanFactory factory;

    @Autowired
    State_Check state_check;
    @Autowired
    kami_api kami_api;
    @Autowired
    SendMessage sendMessage;
    @Autowired
    Daye daye;
    @Autowired
    Lianji lianji;

    public void run() {
        //初始化
        初始化();
        while (true) {
            for (Team team : world.teams) {
                state_check.获取部队状态(team);
                sendMessage.send("当前部队状态为" + team.state);
                switch (team.state) {
                    case "调动":
                    case "停留":
                    case "待命":
                        //该分支匹配到队伍正在待命，可以分配行动
                        team.last_process.run(team);
                        break;
                    default:
                        //该分支匹配到队伍正在行动，不可分配任务

                }
            }
        }
    }

    private void 初始化(){
        for (Team team : world.teams) {
            switch (team.task.type) {
                case "打野":
                    daye.run(team);
                    break;
                case "练级":
                    lianji.run(team);
                    break;
            }

        }
    }
}
