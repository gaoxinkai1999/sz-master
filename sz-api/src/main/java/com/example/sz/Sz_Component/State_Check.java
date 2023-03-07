package com.example.sz.Sz_Component;

import com.example.sz.Cv.Cv;
import com.example.sz.Cv.Cv_Result;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.State;
import com.example.sz.Pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.sz.area_info.部队列表;
import static com.example.sz.state_info.全部状态;

/**
 * 用于检测队伍状态，前置执行条件为 map.前往指定队伍位置
 */
@Component
public class State_Check {
    @Autowired
    Map map;
    @Autowired
    DmSoft dm;
    @Autowired
    Cv cv;

    /**
     * 返回队伍当前状态
     *
     * @param team
     */
    public void 获取部队状态(Team team) {
        //打开部队列表
        map.前往指定队伍位置(team);


        //匹配行动状态，如果没有匹配到，点击 角色头像 继续匹配
        dm.CapturePng(部队列表[0], 部队列表[1], 部队列表[2], 部队列表[3], "jietu.png");

        for (State state : 全部状态) {
            Cv_Result result = cv.不截图匹配(state.path);
            if (result.getScore() > 0.8) {
                //如果匹配到,持续循环匹配
//                System.out.println("当前状态为: " + state.name);
                team.state = state.name;
            }
        }

    }

    /**
     * 实时监控队伍状态
     *
     * @param team
     */
    public void 持续监控部队状态(Team team) {
        获取部队状态(team);
        while (true) {
            dm.CapturePng();
            for (State state : 全部状态) {
                Cv_Result result = cv.不截图匹配(state.path);
                if (result.getScore() > 0.8) {
                    if (!state.name.equals(team.state)) {
                        team.state = state.name;
                        System.out.println("当前状态为: " + state.name);
                    }
                    switch (team.state) {
                        case "停留":
                        case "调动":
                        case "待命":
                            System.out.println("当前行动已完成");
                            return;
                    }

                }
            }
        }
    }

}
