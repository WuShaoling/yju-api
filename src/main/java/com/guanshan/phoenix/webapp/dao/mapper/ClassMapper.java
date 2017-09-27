package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Clazz;

@Mapper
public interface ClassMapper {

    @Insert("INSERT INTO class (course_id, term_id, id, name, size) VALUES (#{courseId}, #{termId}, #{id}, #{name}, #{size})")
    int insert(Clazz clazz);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE class SET course_id=#{courseId}, term_id=#{termId}, id=#{id}, name=#{name}, size=#{size} WHERE id=#{id}")
    int update(Clazz clazz);

    @Select("SELECT * FROM class WHERE id=#{id}")
    Clazz findOne(@Param("id") Integer id);

}