package com.example.kami;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.example.kami.Result.失败;
import static com.example.kami.Result.成功;

@RestController
public class Controller {

    @Autowired
    Dao dao;
    @Autowired
    Info info;
    @Autowired
    Result result;

    @PostMapping("use")
    public Result use(String 卡密, int 点数) {
        Info demo = dao.select(卡密);
        if (demo == null) {
            result.result = 失败;
            result.message = "卡密不存在";
        } else {
            if (demo.剩余点数 >= 点数) {
                dao.卡密使用(卡密, 点数);
                result.result = 成功;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("剩余点数",demo.剩余点数-点数);
                result.message= jsonObject;
                return result;
            } else {
                result.result = 失败;
                result.message = "点数不足";
            }
        }
        return result;

    }
}
