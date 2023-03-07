package com.example.sz.Pojo;

import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * Ai 识别到的敌人信息
 */
@Component
@ToString
public class Enemy {
    public String name;
    public Enemy_Location location;


}
