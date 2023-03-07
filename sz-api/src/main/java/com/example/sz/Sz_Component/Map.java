package com.example.sz.Sz_Component;

import com.example.sz.Cv.Cv;
import com.example.sz.Cv.Cv_Result;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.example.sz.area_info.部队列表;
import static com.example.sz.pic_info.入城;
import static com.example.sz.pic_info.返回;
import static com.example.sz.point_info.*;


/**
 * 检测当前场景并前往指定场景
 */
@Component
public class Map {

    @Autowired
    Cv cv;
    @Autowired
    DmSoft dm;

    /**
     * 返回首页
     */
    public void 返回() {
        Cv_Result cv_result = cv.Temp_Match(返回);
        dm.MoveAndClick(cv_result.getMid().x, cv_result.getMid().y);
    }

    public void 退出到主页面() {
        while (true) {
            Cv_Result cv_result = cv.Temp_Match(返回);
            if (cv_result.getScore() < 0.8) {
                return;
            } else {
                dm.MoveAndClick(cv_result.getMid().x, cv_result.getMid().y);
            }
            dm.Delay(500);
        }
    }

    public void 进入主城() {
        前往主城();
        Point point = cv.模板匹配等待图片出现(入城);
        dm.MoveAndClick(point.x, point.y);
    }

    public void 前往主城() {
        退出到主页面();
        dm.MoveAndClick(部队.x, 部队.y);
        dm.MoveAndClick(地标.x, 地标.y);
        dm.MoveAndClick(主城.x, 主城.y);
    }

    /**
     * 打开主城内部队配置面板，用于ocr识别队伍参数
     *
     * @param team
     */

    public void 打开指定部队配置面板(Team team) {
        进入主城();
        Point point = cv.模板匹配等待图片出现(team.getPic_Path());
        dm.MoveAndClick(point.x, point.y);
    }

    /**
     * 前往指定队伍位置 地图会跳转 部队列表会显示详细信息
     *
     * @param team
     */
    public void 前往指定队伍位置(Team team) {
        退出到主页面();
        dm.MoveAndClick(地标.x, 地标.y);
        dm.MoveAndClick(部队.x, 部队.y);
        //重置列表状态
        dm.MoveAndClick(356, 768);
        dm.MoveAndClick(地标.x, 地标.y);
        dm.MoveAndClick(部队.x, 部队.y);
        //sift匹配到角色头像
        Point point;
        while (true) {
            point = cv.Sift_Match(team.getPic_Path(), 部队列表);
            if (point == null) {
                dm.WheelDown();
                dm.WheelDown();
            } else {
                break;
            }
        }
        dm.MoveAndClick(point.x, point.y);
        dm.Delay(500);
    }


}
