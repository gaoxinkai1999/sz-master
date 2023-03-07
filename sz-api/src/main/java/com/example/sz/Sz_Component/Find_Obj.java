package com.example.sz.Sz_Component;

import com.example.sz.Dm.DmSoft;
import com.example.sz.point_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * 该类用于寻找目标，并将游戏页面转移到该目标处
 */
@Component
public class Find_Obj {
    @Autowired
    DmSoft dm;
    @Autowired
    Map map;


    /**
     * 该方法用于将游戏坐标转换为窗口坐标
     */
    public void a(Point point) {
        //切换到主页面
        map.退出到主页面();
        //打开地图输入坐标
        dm.MoveAndClick(point_info.地图.x, point_info.地图.y);
        dm.MoveAndClick(point_info.地图X坐标输入框.x, point_info.地图X坐标输入框.y);
        //删除坐标
        dm.KeyPressChar("back");
        dm.KeyPressChar("back");
        dm.KeyPressChar("back");
        dm.KeyPressChar("back");
        dm.KeyPressStr(String.valueOf(point.x), 200);

        dm.MoveAndClick(point_info.地图Y坐标输入框.x, point_info.地图Y坐标输入框.y);
        dm.KeyPressChar("back");
        dm.KeyPressChar("back");
        dm.KeyPressChar("back");
        dm.KeyPressChar("back");
        dm.KeyPressStr(String.valueOf(point.y), 200);
        dm.MoveAndClick(point_info.前往.x, point_info.前往.y);
        dm.Delay(500);

    }


}
