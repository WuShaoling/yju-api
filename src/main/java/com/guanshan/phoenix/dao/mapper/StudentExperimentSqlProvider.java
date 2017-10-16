package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentExperiment;
import org.apache.ibatis.jdbc.SQL;

public class StudentExperimentSqlProvider {

    public String insertSelective(StudentExperiment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("student_experiment");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=INTEGER}");
        }
        
        if (record.getExperimentId() != null) {
            sql.VALUES("experiment_id", "#{experimentId,jdbcType=INTEGER}");
        }
        
        if (record.getCloudwareId() != null) {
            sql.VALUES("cloudware_id", "#{cloudwareId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentExperiment record) {
        SQL sql = new SQL();
        sql.UPDATE("student_experiment");
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{studentId,jdbcType=INTEGER}");
        }
        
        if (record.getExperimentId() != null) {
            sql.SET("experiment_id = #{experimentId,jdbcType=INTEGER}");
        }
        
        if (record.getCloudwareId() != null) {
            sql.SET("cloudware_id = #{cloudwareId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}