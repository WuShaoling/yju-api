package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Clazz;
import org.apache.ibatis.jdbc.SQL;

public class ClazzSqlProvider {

    public String insertSelective(Clazz record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("class");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getSemesterId() != null) {
            sql.VALUES("semester_id", "#{semesterId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("course_id", "#{courseId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStudnetNum() != null) {
            sql.VALUES("studnet_num", "#{studnetNum,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Clazz record) {
        SQL sql = new SQL();
        sql.UPDATE("class");
        
        if (record.getSemesterId() != null) {
            sql.SET("semester_id = #{semesterId,jdbcType=INTEGER}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{courseId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStudnetNum() != null) {
            sql.SET("studnet_num = #{studnetNum,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}