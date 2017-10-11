package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.TeacherCourse;
import org.apache.ibatis.jdbc.SQL;

public class TeacherCourseSqlProvider {

    public String insertSelective(TeacherCourse record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("teacher_course");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTeacherId() != null) {
            sql.VALUES("teacher_id", "#{teacherId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("course_id", "#{courseId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TeacherCourse record) {
        SQL sql = new SQL();
        sql.UPDATE("teacher_course");
        
        if (record.getTeacherId() != null) {
            sql.SET("teacher_id = #{teacherId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{courseId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}