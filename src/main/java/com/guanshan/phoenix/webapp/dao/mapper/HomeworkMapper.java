package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Homework;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    @Insert("INSERT INTO homework (update_time, create_time, period_id, description, id, name) VALUES (#{update_time}, #{create_time}, #{period_id}, #{description}, #{id}, #{name})")
    int insert(Homework homework);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE homework SET update_time=#{update_time}, create_time=#{create_time}, period_id=#{period_id}, description=#{description}, id=#{id}, name=#{name} WHERE id=#{id}")
    int update(Homework homework);

    @Select("SELECT * FROM homework WHERE id=#{id}")
    Homework findOne(@Param("id") Integer id);

    @Select("SELECT * FROM homework")
    List<Homework> findAll();

    @Select("SELECT * FROM homework WHERE period_id=#{period_id}")
    List<Homework> findByPeriodId(@Param("period_id") Integer period_id);

}