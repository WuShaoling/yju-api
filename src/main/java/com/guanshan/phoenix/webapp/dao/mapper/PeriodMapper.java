package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Period;

import java.util.List;

@Mapper
public interface PeriodMapper {

    @Insert("INSERT INTO period (update_time, create_time, name, course_id, term_id, id) VALUES (#{update_time}, #{create_time}, #{name}, #{course_id}, #{term_id}, #{id})")
    int insert(Period period);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE period SET update_time=#{update_time}, create_time=#{create_time}, name=#{name}, course_id=#{course_id}, term_id=#{term_id}, id=#{id} WHERE id=#{id}")
    int update(Period period);

    @Select("SELECT * FROM period WHERE id=#{id}")
    Period findOne(@Param("id") Integer id);

    @Select("SELECT * FROM period")
    List<Period> findAll();

    @Select("SELECT * FROM period WHERE course_id=#{course_id}")
    List<Period> findByCourseId(@Param("course_id") Integer courseId);

}