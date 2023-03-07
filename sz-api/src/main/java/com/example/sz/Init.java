package com.example.sz;

import com.example.sz.Dm.DmSoft;
import com.example.sz.Pojo.Team;
import com.example.sz.Pojo.Team_List;
import com.example.sz.Sz_Component.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class Init {


    private String title = "三国志·战略版";

    private int height = 878;

    private int width = 1560;
    @Autowired
    Map map;
    @Autowired
    DmSoft dmSoft;
    @Autowired
    Team_List teams;

    public int hwnd;

    public void 执行初始化() {
        //获取句柄
        hwnd = dmSoft.FindWindow("", title);
        //绑定窗口
        dmSoft.BindWindow(hwnd, "gdi", "dx2", "windows", 0);

        //设置分辨率
        dmSoft.SetClientSize(hwnd, width, height);
        dmSoft.SetWindowState(hwnd, 11);
        //设置全局路劲
        String path = System.getProperty("user.dir") + "/pic";
        dmSoft.SetPath(path);

        //读取队伍信息
        Read_Team_Info();
    }

    private void Read_Team_Info() {
        //打开主城界面
        map.进入主城();
        //录入信息
        Point left = new Point(55, 722);
        Point right = new Point(123, 791);
        for (int i = 0; i < 5; i++) {
            String path = "\\pic\\team\\" + i + ".png";
            for (Team team : teams.getTeams()) {
                if (team.TeamId == i) {
                    team.setPic_Path(path);
                    dmSoft.CapturePng(left.x, left.y, right.x, right.y, "/team/" + i + ".png");
                }
            }
            left.setLocation(left.x + 306, left.y);
            right.setLocation(right.x + 306, right.y);
        }
        //返回主页面
        map.返回();

    }

}
