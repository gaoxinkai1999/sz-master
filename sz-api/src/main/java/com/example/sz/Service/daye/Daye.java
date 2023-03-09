package com.example.sz.Service.daye;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team;
import com.example.sz.Process.AddSoldiers;
import com.example.sz.Process.FindEnemyAndGoOn;
import com.example.sz.Process.Move;
import com.example.sz.Service.Service;
import com.example.sz.Sz_Component.*;
import com.example.sz.baidu.Ai;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    Location location;


    @Autowired
    public AddSoldiers addSoldiers;
    @Autowired
    public Move move;
    @Autowired
    public FindEnemyAndGoOn findEnemyAndGoOn;



    @Override
    public void run(Team team) {
        配置处理(team);
        team.last_process=addSoldiers;
    }

    private void 配置处理(Team team) {

        team.task.config = JSONObject.parseObject(team.task.配置JSON, Daye_Config.class);
        Daye_Config 配置 = (Daye_Config) (team.task.config);
        配置.生成矩阵点信息();
    }

}
