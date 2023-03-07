package com.example.sz.Service.daye;

import lombok.ToString;

import java.awt.*;

@SuppressWarnings("all")
@ToString
public class array_point_info {

    public Point point = new Point();
    public boolean IsDie = false;

    public array_point_info(Point point) {
        this.point.setLocation(point);
    }
}
