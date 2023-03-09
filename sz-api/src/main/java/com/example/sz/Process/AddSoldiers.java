package com.example.sz.Process;

import com.example.sz.Pojo.Team;
import com.example.sz.Service.daye.Daye;
import com.example.sz.Service.daye.Daye_Config;
import com.example.sz.Sz_Component.Action;
import com.example.sz.Sz_Component.ZhengBing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检查兵力并前往征兵处
 */
@Component
public class AddSoldiers implements Process {
    @Autowired
    Daye daye;
    @Autowired
    ZhengBing zhengBing;
    @Autowired
    Action action;

    @Override
    public void run(Team team) {
        if ("停留".equals(team.state)) {
            action.回城(team);
        } else {
            Daye_Config config = (Daye_Config) team.task.config;
            //需要征兵
            if (zhengBing.识别兵力(team)) {
                if (config.是否主城征兵) {
                    if (team.state.equals("待命")){
                        zhengBing.主城征兵(team);
                        team.last_process=daye.move;
                    }else {
                        action.回城(team);
                    }
                } else {
                    if (team.state.equals("待命")){
                        zhengBing.主城征兵(team);
                    }else {
                        zhengBing.调动点征兵(team);
                    }
                    team.last_process=daye.move;
                }
            } else {
                //不需要征兵,直接调动
                team.last_process=daye.move;
            }
        }
    }

}

