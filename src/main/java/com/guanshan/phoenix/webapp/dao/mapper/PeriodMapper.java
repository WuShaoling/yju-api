package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Period;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface PeriodMapper {

    @Insert("INSERT INTO period (course_id, name), " +
            "VALUES (#{course_id}, #{name}")
    int insert(Period period);

    @Delete("DELETE FROM period WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE period SET course_id=#{course_id}, name=#{name} " +
            "WHERE id=#{id}")
    int update(Period period);

    @Select("SELECT * FROM period WHERE id=#{id}")
    Period findOne(@Param("id") Integer id);

    @Select("SELECT * FROM period")
    List<Period> findAll();

    @Select("SELECT * FROM period WHERE course_id=#{courseId}")
    List<Period> findAllByCourseId(@Param("courseId") Integer courseId);
}
