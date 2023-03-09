package com.example.sz.Sz_Component;

import com.example.sz.Cv.Cv;
import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.example.sz.pic_info.分享;
import static com.example.sz.pic_info.调动;
import static com.example.sz.point_info.坐标分享;
import static com.example.sz.point_info.窗口中心;

/**
 * 该类用于寻找主城坐标或队伍当前坐标
 */
@Component
public class Location {
    @Autowired
    Map map;
    @Autowired
    Cv cv;
    @Autowired
    DmSoft dm;
    @Autowired
    Action action;
    @Autowired
    Find_Obj fing;

    public Point 获取主城坐标() {
        map.前往主城();
        Point point = cv.模板匹配等待图片出现(分享);
        dm.MoveAndClick(point.x, point.y);
        dm.MoveAndClick(坐标分享.x, 坐标分享.y);
        return 读取剪切板坐标并解析成点();

    }

    public Point 获取队伍坐标(Team team) {
        map.前往指定队伍位置(team);
        dm.MoveAndClick(窗口中心.x, 窗口中心.y);
        Point point = cv.模板匹配等待图片出现(分享);
        dm.MoveAndClick(point.x, point.y);
        dm.MoveAndClick(坐标分享.x, 坐标分享.y);
        return 读取剪切板坐标并解析成点();
    }

    /**

     查询队伍是否在指定调动点，不在则前往该调动点
     如果需要前往返回true 不需要则返回false
     */
    public boolean 前往调动点(Team team,Point point){
        Point 队伍坐标 = 获取队伍坐标(team);
        if (!队伍坐标.equals(point)){
            fing.a(point);
            action.run(team,调动);
            return true;
        }
        return false;
    }

    private Point 读取剪切板坐标并解析成点() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);
        Point point = new Point();

        try {
            String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
            String[] split = text.split(",");
            point.setLocation(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } catch (UnsupportedFlavorException | IOException e) {
            throw new RuntimeException(e);
        }
        return point;
    }
}
