package com.example.sz.Service.daye;

import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("all")
@ToString
public class move_point_info {
    //调用点坐标信息
    public Point point = new Point();
    public boolean 是否失效 = false;
    public ArrayList<array_point_info> 矩阵 = new ArrayList<>();

    public move_point_info(Point point, int 圈数) {
        this.point.setLocation(point);
        生成矩阵(圈数);
    }

    private void 生成矩阵(int 圈数) {

        Point 临时坐标 = new Point(point);
        矩阵.add(new array_point_info(临时坐标));
        for (int i = 1; i <= 圈数; i++) {
            临时坐标.setLocation(临时坐标.x + 10 * i, 临时坐标.y);
            矩阵.add(new array_point_info(临时坐标));

            for (int j = 1; j <= i * 2; j++) {
                临时坐标.setLocation(临时坐标.x - 5, 临时坐标.y + 5);
                矩阵.add(new array_point_info(临时坐标));
            }
            for (int j = 1; j <= i * 2; j++) {
                临时坐标.setLocation(临时坐标.x - 5, 临时坐标.y - 5);
                矩阵.add(new array_point_info(临时坐标));
            }
            for (int j = 1; j <= i * 2; j++) {
                临时坐标.setLocation(临时坐标.x + 5, 临时坐标.y - 5);
                矩阵.add(new array_point_info(临时坐标));
            }
            for (int j = 1; j <= (i * 2) - 1; j++) {
                临时坐标.setLocation(临时坐标.x + 5, 临时坐标.y + 5);
                矩阵.add(new array_point_info(临时坐标));
            }
            //一圈后重置坐标
            临时坐标.setLocation(point);
        }
    }
}

