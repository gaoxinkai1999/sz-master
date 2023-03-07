package com.example.sz.Pojo;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 用于保存全部的队伍信息
 */
@Component
@Data
public class Team_List {
    private List<Team> teams;
    private String 卡密;
}
