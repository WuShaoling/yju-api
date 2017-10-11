package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Course;
import org.apache.ibatis.jdbc.SQL;

public class CourseSqlProvider {

    public String insertSelective(Course record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("course");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getSemesterId() != null) {
            sql.VALUES("semester_id", "#{semesterId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseSid() != null) {
            sql.VALUES("course_sid", "#{courseSid,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getImage() != null) {
            sql.VALUES("image", "#{image,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.VALUES("date", "#{date,jdbcType=DATE}");
        }
        
        if (record.getDuration() != null) {
            sql.VALUES("duration", "#{duration,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentNumber() != null) {
            sql.VALUES("student_number", "#{studentNumber,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Course record) {
        SQL sql = new SQL();
        sql.UPDATE("course");
        
        if (record.getSemesterId() != null) {
            sql.SET("semester_id = #{semesterId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseSid() != null) {
            sql.SET("course_sid = #{courseSid,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getImage() != null) {
            sql.SET("image = #{image,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{date,jdbcType=DATE}");
        }
        
        if (record.getDuration() != null) {
            sql.SET("duration = #{duration,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentNumber() != null) {
            sql.SET("student_number = #{studentNumber,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}