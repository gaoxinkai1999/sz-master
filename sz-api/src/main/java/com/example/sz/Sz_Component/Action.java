package com.example.sz.Sz_Component;

import com.example.sz.Cv.Cv;
import com.example.sz.Cv.Cv_Result;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.example.sz.area_info.出发地选择;
import static com.example.sz.pic_info.*;
import static com.example.sz.point_info.自动回城勾选;
import static com.example.sz.point_info.行动确认;

/**
 * 该类用于控制队伍的各种行动方式
 */
@Component
public class Action {
    @Autowired
    Cv cv;
    @Autowired
    DmSoft dm;
    @Autowired
    Map map;


    /**
     * 攻占 行军 讨伐 攻城 驻守 调动
     *
     * @param team
     * @param method
     */
    public void run(Team team, String method) {
        Point point = cv.模板匹配等待图片出现(method);
        dm.MoveAndClick(point.x, point.y);

        选择行动目标(team);
        //  设置自动回城
        switch (method) {
            case 攻占:
            case 攻城:
            case 讨伐:
            case 扫荡:
                double score = cv.Temp_Match(自动回城).getScore();
                if (score < 0.8) {
                    dm.MoveAndClick(自动回城勾选.x, 自动回城勾选.y);
                }
                break;
        }
        //行动确认
        dm.MoveAndClick(行动确认.x, 行动确认.y);
        //危险提示
        Cv_Result result = cv.Temp_Match(坚持出征);
        if (result.getScore() > 0.8) {
            dm.MoveAndClick(result.getMid().x, result.getMid().y);
        }
    }

    public void 回城(Team team) {
        map.前往指定队伍位置(team);
        Point point = cv.模板匹配等待图片出现(回城);
        dm.MoveAndClick(point.x, point.y);
        //点击确定回城
        Cv_Result result = cv.Temp_Match(确定);
        if (result.getScore() > 0.8) {
            dm.MoveAndClick(result.getMid().x, result.getMid().y);
        }
    }

    /**
     * 目前版本只能实现找最近队伍
     *
     * @param team
     */
    private void 选择行动目标(Team team) {
        Point point = cv.SIFT匹配等待图片出现(team.getPic_Path(), 出发地选择);
        dm.MoveAndClick(point.x, point.y);
        Point point1 = cv.模板匹配等待图片出现(team.getPic_Path());
        dm.MoveAndClick(point1.x, point1.y);
    }

}
