package com.example.sz;

import com.example.sz.Pojo.Team;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ToString
public class World {
    public String 卡密;
    public int 剩余点数;
    public boolean 暂停 =false;
    public boolean 停止 =false;
    public ArrayList<Message> messages;
    public List<Team> teams;
}
