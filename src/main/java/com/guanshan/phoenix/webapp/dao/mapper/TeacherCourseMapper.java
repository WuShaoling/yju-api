package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.TeacherCourse;

@Mapper
public interface TeacherCourseMapper {

    @Insert("INSERT INTO teacher_course (teacher_id, term_id, id, course_id) VALUES (#{teacherId}, #{termId}, #{id}, #{courseId})")
    int insert(TeacherCourse teacher_course);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE teacher_course SET teacher_id=#{teacherId}, term_id=#{termId}, id=#{id}, course_id=#{courseId} WHERE id=#{id}")
    int update(TeacherCourse teacher_course);

    @Select("SELECT * FROM teacher_course WHERE id=#{id}")
    TeacherCourse findOne(@Param("id") Integer id);

}