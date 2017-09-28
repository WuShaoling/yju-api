package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Clazz;

import java.util.List;

@Mapper
public interface ClassMapper {

    @Insert("INSERT INTO class (course_id, term_id, id, name, size) VALUES (#{course_id}, #{term_id}, #{id}, #{name}, #{size})")
    int insert(Clazz clazz);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE class SET course_id=#{course_id}, term_id=#{term_id}, id=#{id}, name=#{name}, size=#{size} WHERE id=#{id}")
    int update(Clazz clazz);

    @Select("SELECT * FROM class WHERE id=#{id}")
    Clazz findOne(@Param("id") Integer id);

    @Select("SELECT * FROM class")
    List<Clazz> findAll();

}