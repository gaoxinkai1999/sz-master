package com.example.sz.Service;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Task {
    //任务类型
    public String type;
    public boolean 是否完成;
    public String 配置JSON;

    public Config config;
}
