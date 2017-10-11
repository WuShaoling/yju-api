package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentHomework;
import org.apache.ibatis.jdbc.SQL;

public class StudentHomeworkSqlProvider {

    public String insertSelective(StudentHomework record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("student_homework");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=INTEGER}");
        }
        
        if (record.getHomeworkId() != null) {
            sql.VALUES("homework_id", "#{homeworkId,jdbcType=INTEGER}");
        }
        
        if (record.getPeriodId() != null) {
            sql.VALUES("period_id", "#{periodId,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            sql.VALUES("score", "#{score,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentHomework record) {
        SQL sql = new SQL();
        sql.UPDATE("student_homework");
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{studentId,jdbcType=INTEGER}");
        }
        
        if (record.getHomeworkId() != null) {
            sql.SET("homework_id = #{homeworkId,jdbcType=INTEGER}");
        }
        
        if (record.getPeriodId() != null) {
            sql.SET("period_id = #{periodId,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            sql.SET("score = #{score,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}