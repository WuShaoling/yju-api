package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.RoleNavlist;
import org.apache.ibatis.jdbc.SQL;

public class RoleNavlistSqlProvider {

    public String insertSelective(RoleNavlist record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("role_navlist");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getRole() != null) {
            sql.VALUES("role", "#{role,jdbcType=INTEGER}");
        }
        
        if (record.getNavlistId() != null) {
            sql.VALUES("navlist_id", "#{navlistId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RoleNavlist record) {
        SQL sql = new SQL();
        sql.UPDATE("role_navlist");
        
        if (record.getRole() != null) {
            sql.SET("role = #{role,jdbcType=INTEGER}");
        }
        
        if (record.getNavlistId() != null) {
            sql.SET("navlist_id = #{navlistId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}