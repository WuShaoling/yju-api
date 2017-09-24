package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.StudentCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCourseMapper {

    @Insert("INSERT INTO student_course (student_id, course_id), " +
            "VALUES (#{student_id}, #{course_id}")
    int insert(StudentCourse studentCourse);

    @Delete("DELETE FROM student_course WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE student_course SET student_id=#{student_id}, course_id=#{course_id} " +
            "WHERE id=#{id}")
    int update(StudentCourse studentCourse);

    @Select("SELECT * FROM student_course WHERE id=#{id}")
    StudentCourse findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student_course")
    List<StudentCourse> findAll();

    @Select("SELECT course_id FROM student_course WHERE student_id=#{studentId}")
    List<Integer> findAllCourseIdByStudentId(@Param("studentId") Integer studentId);
}
