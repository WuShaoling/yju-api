package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentCourse;
import org.apache.ibatis.jdbc.SQL;

public class StudentCourseSqlProvider {

    public String insertSelective(StudentCourse record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("student_course");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("course_id", "#{courseId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentCourse record) {
        SQL sql = new SQL();
        sql.UPDATE("student_course");
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{studentId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{courseId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}