package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.util.List;


@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO course (name, image, description, teacher, date, duration, student_num), " +
            "VALUES (#{name}, #{image}, #{description}, #{teacher}, #{date}, #{duration}, #{student_num}")
    int insert(Course course);

    @Delete("DELETE FROM course WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE course SET name=#{name}, image=#{image}, description=#{description}, " +
            "teacher=#{teacher}, date=#{date}, duration=#{duration}, student_num=#{student_num} " +
            "WHERE id=#{id}")
    int update(Course course);

    @Select("SELECT * FROM course WHERE id=#{id}")
    Course findOne(@Param("id") Integer id);

    @Select("SELECT * FROM course")
    List<Course> findAll();
}
