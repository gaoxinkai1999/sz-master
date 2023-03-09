package com.example.sz.Process;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Enemy;
import com.example.sz.Pojo.Team;
import com.example.sz.Service.daye.Daye;
import com.example.sz.Service.daye.Daye_Config;
import com.example.sz.Sz_Component.Action;
import com.example.sz.Sz_Component.Find_Obj;
import com.example.sz.Sz_Component.Location;
import com.example.sz.baidu.Ai;
import com.example.sz.kami.Kami;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

import static com.example.sz.pic_info.讨伐;

/**
 * 寻找野怪并前往讨伐
 */
@Component
public class FindEnemyAndGoOn implements Process {

    @Autowired
    Daye daye;
    @Autowired
    Location location;
    @Autowired
    Find_Obj find;
    @Autowired
    DmSoft dm;
    @Autowired
    Ai ai;
    @Autowired
    Action action;
    @Autowired
    Kami kami;

    @Override
    public void run(Team team) {
        Daye_Config config = (Daye_Config) team.task.config;
        kami.use(20);
        //开始遍历
        for (com.example.sz.Service.daye.move_point_info move_point_info : config.move_point_infos) {
            //判断调用点是否完成
            if (!move_point_info.是否失效) {
                for (com.example.sz.Service.daye.array_point_info array_point_info : move_point_info.矩阵) {
                    //判断矩阵点是否完成
                    if (!array_point_info.IsDie) {
                        Point point = array_point_info.point;
                        array_point_info.IsDie = true;
                        //是否是最后一个
                        if (move_point_info.矩阵.indexOf(array_point_info) == move_point_info.矩阵.size() - 1) {
                            move_point_info.是否失效 = true;
                        }
                        if (前往讨伐(team, point)) {
                            team.last_process = daye.addSoldiers;
                            return;
                        }
                    }
                }

            }
        }
    }

    private boolean 前往讨伐(Team team, Point point) {
        find.a(point);
        dm.Delay(1000);
        //ai 找图
        dm.CapturePng();
        JSONObject start = ai.start(System.getProperty("user.dir") + "/pic/jietu.png");

        //解析出结果数组
        JSONArray results = start.getJSONArray("results");

        //解析成enemy对象
        List<Enemy> enemies = results.toJavaList(Enemy.class);


        for (Enemy enemy : enemies) {
            if (enemy.name.equals("7级地") || enemy.name.equals("8级地") || enemy.name.equals("9级地")) {
                Point mid = new Point(enemy.location.left + enemy.location.width / 2, enemy.location.top + enemy.location.height / 2);

                //移动到窗口中心
                dm.Drag(mid, new Point(786, 436));
                dm.Delay(1000);
                dm.MoveAndClick(786, 436);
                //开始讨伐
                action.run(team, 讨伐);
                return true;
            }
        }
        return false;
    }
}
