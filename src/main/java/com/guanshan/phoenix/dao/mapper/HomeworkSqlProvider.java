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
        
        if (record.getModuleId() != null) {
            sql.VALUES("module_id", "#{moduleId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishDate() != null) {
            sql.VALUES("publish_date", "#{publishDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeadlineDate() != null) {
            sql.VALUES("deadline_date", "#{deadlineDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClassId() != null) {
            sql.VALUES("class_id", "#{classId,jdbcType=INTEGER}");
        }

        if(record.getHomeworkContent() != null){
            sql.VALUES("homework_content", "#{homeworkContent, jdbcType=LONGVARCHAR}");
        }

        if(record.getImageId() != null){
            sql.VALUES("image_id", "#{imageId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Homework record) {
        SQL sql = new SQL();
        sql.UPDATE("homework");
        
        if (record.getModuleId() != null) {
            sql.SET("module_id = #{moduleId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishDate() != null) {
            sql.SET("publish_date = #{publishDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeadlineDate() != null) {
            sql.SET("deadline_date = #{deadlineDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClassId() != null) {
            sql.SET("class_id = #{classId,jdbcType=INTEGER}");
        }

        if(record.getHomeworkContent() != null){
            sql.SET("homework_content = #{homeworkContent, jdbcType=LONGVARCHAR}");
        }

        if(record.getImageId() != null){
            sql.SET("image_id = #{imageId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}