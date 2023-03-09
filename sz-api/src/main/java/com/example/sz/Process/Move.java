package com.example.sz.Process;

import com.example.sz.Pojo.Team;
import com.example.sz.Service.daye.Daye;
import com.example.sz.Service.daye.Daye_Config;
import com.example.sz.Sz_Component.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 调动到营帐
 */
@Component
public class Move  implements Process{
    @Autowired
    Daye daye;
    @Autowired
    Location location;
    @Override
    public void run(Team team) {
        Daye_Config config = (Daye_Config)team.task.config;
        //开始遍历
        for (com.example.sz.Service.daye.move_point_info move_point_info : config.move_point_infos ) {
            //判断调用点是否完成
            if (!move_point_info.是否失效) {
               location.前往调动点(team, move_point_info.point);
                team.last_process= daye.findEnemyAndGoOn;
               return;

            }
        }
    }
}
