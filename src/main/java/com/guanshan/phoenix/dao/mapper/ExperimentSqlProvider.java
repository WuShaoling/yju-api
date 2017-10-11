package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Experiment;
import org.apache.ibatis.jdbc.SQL;

public class ExperimentSqlProvider {

    public String insertSelective(Experiment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("experiment");
        
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
        
        if (record.getCloudwareId() != null) {
            sql.VALUES("cloudware_id", "#{cloudwareId,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishDate() != null) {
            sql.VALUES("publish_date", "#{publishDate,jdbcType=DATE}");
        }
        
        if (record.getExpireDate() != null) {
            sql.VALUES("expire_date", "#{expireDate,jdbcType=DATE}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Experiment record) {
        SQL sql = new SQL();
        sql.UPDATE("experiment");
        
        if (record.getPeriodId() != null) {
            sql.SET("period_id = #{periodId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getCloudwareId() != null) {
            sql.SET("cloudware_id = #{cloudwareId,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishDate() != null) {
            sql.SET("publish_date = #{publishDate,jdbcType=DATE}");
        }
        
        if (record.getExpireDate() != null) {
            sql.SET("expire_date = #{expireDate,jdbcType=DATE}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}