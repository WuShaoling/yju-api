package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Course;

@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO course (teacher_id, update_time, description, student_num, term_id, date, duration, craete_time, id, name) VALUES (#{teacherId}, #{updateTime}, #{description}, #{studentNum}, #{termId}, #{date}, #{duration}, #{craeteTime}, #{id}, #{name})")
    int insert(Course course);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE course SET teacher_id=#{teacherId}, update_time=#{updateTime}, description=#{description}, student_num=#{studentNum}, term_id=#{termId}, date=#{date}, duration=#{duration}, craete_time=#{craeteTime}, id=#{id}, name=#{name} WHERE id=#{id}")
    int update(Course course);

    @Select("SELECT * FROM course WHERE id=#{id}")
    Course findOne(@Param("id") Integer id);

}