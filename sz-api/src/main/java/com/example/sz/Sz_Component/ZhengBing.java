package com.example.sz.Sz_Component;

import com.alibaba.fastjson.JSONObject;
import com.example.sz.Cv.Cv;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team;
import com.example.sz.baidu.Ocr;
import com.example.sz.pic_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.example.sz.area_info.部队兵力;
import static com.example.sz.point_info.快速分兵;
import static com.example.sz.point_info.确定分兵;

/**
 * 征兵逻辑
 */
@Component
public class ZhengBing {
    @Autowired
    Map map;
    @Autowired
    DmSoft dm;
    @Autowired
    Action action;
    @Autowired
    Location location;
    @Autowired
    Find_Obj find;
    @Autowired
    Cv cv;
    @Autowired
    Ocr ocr;
    @Autowired
    SendMessage sendMessage;


    /**
     * 判断是否需要征兵 如果需要征兵返回true
     *
     * @param team
     * @return
     */
    public boolean 识别兵力(Team team) {
        map.打开指定部队配置面板(team);
        dm.CapturePng(部队兵力[0], 部队兵力[1], 部队兵力[2], 部队兵力[3], "jietu.png");
        JSONObject result = ocr.start(System.getProperty("user.dir") + "/pic/jietu.png");
        String 兵力数据 = result.getJSONArray("words_result").getJSONObject(0).getString("words");
        String[] split = 兵力数据.split("/");
        int 当前兵力 = Integer.parseInt(split[0]);
        int 总兵力 = Integer.parseInt(split[1]);
        sendMessage.send("当前兵力:" + 当前兵力);
        //判断阈值
        return ((当前兵力 * 1.0) / 总兵力 < 0.7);
    }

    /**
     * 因为征兵前已识别兵力 窗口已处在部队配置面板，所以可以直接操作
     *
     * @param team
     */
    public void 主城征兵(Team team) {
        //已经在主城

        dm.MoveAndClick(快速分兵.x, 快速分兵.y);
        dm.MoveAndClick(确定分兵.x, 确定分兵.y);

    }

    public void 调动点征兵(Team team) {

        //是否为指定调动点
        map.前往指定队伍位置(team);

        //征兵
        Point 征兵坐标 = cv.模板匹配等待图片出现(pic_info.征兵);
        dm.MoveAndClick(征兵坐标.x, 征兵坐标.y);
        Point 队伍坐标 = cv.模板匹配等待图片出现(team.getPic_Path());
        dm.MoveAndClick(队伍坐标.x, 队伍坐标.y);
        Point 最大 = cv.模板匹配等待图片出现(pic_info.最大征兵);
        dm.MoveAndClick(最大.x, 最大.y);
        Point 开始征兵 = cv.模板匹配等待图片出现(pic_info.开始征兵);
        dm.MoveAndClick(开始征兵.x, 开始征兵.y);

    }

}
