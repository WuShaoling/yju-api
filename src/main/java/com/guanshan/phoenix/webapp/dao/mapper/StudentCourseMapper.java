package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.StudentCourse;

import java.util.List;

@Mapper
public interface StudentCourseMapper {

    @Insert("INSERT INTO student_course (course_id, student_id, id, term_id) VALUES (#{course_id}, #{student_id}, #{id}, #{term_id})")
    int insert(StudentCourse student_course);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student_course SET course_id=#{course_id}, student_id=#{student_id}, id=#{id}, term_id=#{term_id} WHERE id=#{id}")
    int update(StudentCourse student_course);

    @Select("SELECT * FROM student_course WHERE id=#{id}")
    StudentCourse findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student_course")
    List<StudentCourse> findAll();

    @Select("SELECT * FROM student_course WHERE student_id=#{student_id}")
    List<StudentCourse> findByStudentId(@Param("student_id") int student_id);

}