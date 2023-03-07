package com.example.sz.Service.daye;

import com.example.sz.Service.Config;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@ToString
public class Daye_Config implements Config {

    //指定调动点
    public Point[] 调动点;
    //指定兵力阈值
    public double 阈值;
    public int 圈数;
    //是否主城征兵
    public boolean 是否主城征兵;
    public move_point_info[] move_point_infos;

    public void 生成矩阵点信息() {
        move_point_infos = new move_point_info[调动点.length];
        for (int i = 0; i < 调动点.length; i++) {
            Point point = 调动点[i];
            move_point_info move_point_info = new move_point_info(point, 圈数);
            move_point_infos[i] = move_point_info;
        }
    }
}
