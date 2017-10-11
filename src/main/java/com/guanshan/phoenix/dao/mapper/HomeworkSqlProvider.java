package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Homework;
import org.apache.ibatis.jdbc.SQL;

public class HomeworkSqlProvider {

    public String insertSelective(Homework record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("homework");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getPeriodId() != null) {
            sql.VALUES("period_id", "#{periodId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Homework record) {
        SQL sql = new SQL();
        sql.UPDATE("homework");
        
        if (record.getPeriodId() != null) {
            sql.SET("period_id = #{periodId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}