package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Homework;

@Mapper
public interface HomeworkMapper {

    @Insert("INSERT INTO homework (update_time, period_id, description, craete_time, id, name) VALUES (#{updateTime}, #{periodId}, #{description}, #{craeteTime}, #{id}, #{name})")
    int insert(Homework homework);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE homework SET update_time=#{updateTime}, period_id=#{periodId}, description=#{description}, craete_time=#{craeteTime}, id=#{id}, name=#{name} WHERE id=#{id}")
    int update(Homework homework);

    @Select("SELECT * FROM homework WHERE id=#{id}")
    Homework findOne(@Param("id") Integer id);

}