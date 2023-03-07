package com.example.kami;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Controller {

    @Autowired
    Dao dao;
    @Autowired
    Info info;

    /**
     * 生成卡密
     *
     * @return
     */
    @PostMapping("add/demo")
    public String 生成测试卡() {
        info.生成测试卡();
        dao.add(info);
        return info.卡密;

    }

    @PostMapping("add/day")
    public String 生成天卡() {
        info.生成天卡();
        dao.add(info);
        return info.卡密;
    }

    /**
     * 卡密开始计时
     */
    @PostMapping("use")
    public boolean use(String 卡密) {
        Info select = dao.select(卡密);
        if (select != null) {
            if (select.到期时间 == null) {
                select.生成到期时间();
                dao.卡密使用(select);
                return true;
            } else {
                Date date = new Date();
                if (select.到期时间.after(date)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
