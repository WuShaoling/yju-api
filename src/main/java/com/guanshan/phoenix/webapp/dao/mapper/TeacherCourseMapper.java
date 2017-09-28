package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.TeacherCourse;

import java.util.List;

@Mapper
public interface TeacherCourseMapper {

    @Insert("INSERT INTO teacher_course (teacher_id, term_id, id, course_id) VALUES (#{teacher_id}, #{term_id}, #{id}, #{course_id})")
    int insert(TeacherCourse teacher_course);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE teacher_course SET teacher_id=#{teacher_id}, term_id=#{term_id}, id=#{id}, course_id=#{course_id} WHERE id=#{id}")
    int update(TeacherCourse teacher_course);

    @Select("SELECT * FROM teacher_course WHERE id=#{id}")
    TeacherCourse findOne(@Param("id") Integer id);

    @Select("SELECT * FROM teacher_course")
    List<TeacherCourse> findAll();

}