package com.example.sz.Pojo;

import com.example.sz.Process.Process;
import com.example.sz.Service.Task;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Team {

    //信息
    public String Pic_Path;
    public int TeamId;

    public String state;

    public Task task;

    public Process last_process;

}
