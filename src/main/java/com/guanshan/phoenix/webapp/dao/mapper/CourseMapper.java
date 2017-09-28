package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Course;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO course (teacher_id, update_time, description, student_num, term_id, date, create_time, duration, id, name) VALUES (#{teacher_id}, #{update_time}, #{description}, #{student_num}, #{term_id}, #{date}, #{create_time}, #{duration}, #{id}, #{name})")
    int insert(Course course);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE course SET teacher_id=#{teacher_id}, update_time=#{update_time}, description=#{description}, student_num=#{student_num}, term_id=#{term_id}, date=#{date}, create_time=#{create_time}, duration=#{duration}, id=#{id}, name=#{name} WHERE id=#{id}")
    int update(Course course);

    @Select("SELECT * FROM course WHERE id=#{id}")
    Course findOne(@Param("id") Integer id);

    @Select("SELECT * FROM course")
    List<Course> findAll();

}