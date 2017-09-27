package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.StudentCourse;

@Mapper
public interface StudentCourseMapper {

    @Insert("INSERT INTO student_course (course_id, student_id, id, term_id) VALUES (#{courseId}, #{studentId}, #{id}, #{termId})")
    int insert(StudentCourse student_course);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student_course SET course_id=#{courseId}, student_id=#{studentId}, id=#{id}, term_id=#{termId} WHERE id=#{id}")
    int update(StudentCourse student_course);

    @Select("SELECT * FROM student_course WHERE id=#{id}")
    StudentCourse findOne(@Param("id") Integer id);

}