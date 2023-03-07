package com.example.kami;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Dao {


    @Select("select * from db01.三战卡密 where 卡密=#{卡密}")
    Info select(String 卡密);

    @Insert("insert into db01.三战卡密(卡密,有效期) values (#{卡密},#{有效期})")
    void add(Info info);

    @Update("UPDATE db01.三战卡密 SET 到期时间=#{到期时间} WHERE 卡密 = #{卡密}")
    void 卡密使用(Info info);
}
