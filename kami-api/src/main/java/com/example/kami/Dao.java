package com.example.kami;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Dao {


    @Select("select * from db01.三战卡密 where 卡密=#{卡密}")
    Info select(String 卡密);


    @Update("UPDATE db01.三战卡密 SET 剩余点数=剩余点数-#{点数} WHERE 卡密 = #{卡密}")
    int 卡密使用(String 卡密,int 点数);
}
