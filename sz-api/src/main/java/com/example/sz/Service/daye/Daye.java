package com.example.sz.Service.daye;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Enemy;
import com.example.sz.Pojo.Team;
import com.example.sz.Service.Service;
import com.example.sz.Sz_Component.Action;
import com.example.sz.Sz_Component.Find_Obj;
import com.example.sz.Sz_Component.State_Check;
import com.example.sz.Sz_Component.ZhengBing;
import com.example.sz.baidu.Ai;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

import static com.example.sz.pic_info.讨伐;

/**
 * 打野必要参数
 * 调动坐标点 打野等级限制 补兵模式
 * <p>
 * 打野流程
 * 补兵 调动  找野怪 讨伐 回城
 */
@org.springframework.stereotype.Service
public class Daye implements Service {

    @Autowired
    DmSoft dm;
    @Autowired
    Find_Obj fing;
    @Autowired
    Action action;
    @Autowired
    Ai ai;

    @Autowired
    State_Check state_check;
    @Autowired
    ZhengBing zhengbing;

    @Override
    public void run(Team team) {


        if (zhengbing.识别兵力(team)) {
            zhengbing.主城征兵(team);
        }
        move_point_info[] move_point_infos = 配置处理(team);

        Point point;
        //开始遍历
        for (move_point_info move_point_info : move_point_infos) {
            //判断调用点是否完成
            if (!move_point_info.是否失效) {
                for (array_point_info array_point_info : move_point_info.矩阵) {
                    //判断矩阵点是否完成
                    if (!array_point_info.IsDie) {
                        point = array_point_info.point;
                        array_point_info.IsDie = true;
                        if (前往讨伐(team, point)) {
                            return;
                        }
                    }
                }
                //遍历结束后，设置调用点信息为已失效
                move_point_info.是否失效 = true;
            }
        }
        team.task.是否完成 = true;
    }

    private move_point_info[] 配置处理(Team team) {


        if (team.task.config == null) {
            team.task.config = JSONObject.parseObject(team.task.配置JSON, Daye_Config.class);
        }
        Daye_Config 配置 = (Daye_Config) (team.task.config);

        if (配置.move_point_infos == null) {
            配置.生成矩阵点信息();
        }
        return 配置.move_point_infos;
    }

    /**
     * 去该坐标找野怪并讨伐，如果成功讨伐，返回true
     *
     * @param point
     * @return
     */
    private boolean 前往讨伐(Team team, Point point) {
        fing.a(point);
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
                dm.Drag(mid, new Point(570, 364));
                dm.MoveAndClick(570, 364);
                //开始讨伐
                action.run(team, 讨伐);
                return true;
            }
        }
        return false;
    }
}
