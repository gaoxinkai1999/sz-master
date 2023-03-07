package com.example.sz.Pojo;

import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * Ai 获取到的敌人位置信息
 */
@Component
@ToString
public class Enemy_Location {
    public int top;
    public int left;
    public int width;
    public int height;


}
