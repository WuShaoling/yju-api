package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Cloudware;
import org.apache.ibatis.jdbc.SQL;

public class CloudwareSqlProvider {

    public String insertSelective(Cloudware record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cloudware");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getWebSocket() != null) {
            sql.VALUES("web_socket", "#{webSocket,jdbcType=VARCHAR}");
        }
        
        if (record.getServiceId() != null) {
            sql.VALUES("service_id", "#{serviceId,jdbcType=VARCHAR}");
        }
        
        if (record.getInstance() != null) {
            sql.VALUES("instance", "#{instance,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Cloudware record) {
        SQL sql = new SQL();
        sql.UPDATE("cloudware");
        
        if (record.getWebSocket() != null) {
            sql.SET("web_socket = #{webSocket,jdbcType=VARCHAR}");
        }
        
        if (record.getServiceId() != null) {
            sql.SET("service_id = #{serviceId,jdbcType=VARCHAR}");
        }
        
        if (record.getInstance() != null) {
            sql.SET("instance = #{instance,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}