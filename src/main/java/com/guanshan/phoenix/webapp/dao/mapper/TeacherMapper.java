package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface TeacherMapper {

    @Insert("INSERT INTO teacher (name, username, password), " +
            "VALUES (#{name}, #{username}, #{password}")
    int insert(Teacher teacher);

    @Delete("DELETE FROM teacher WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE teacher SET name=#{name}, username=#{username}, password=#{password} " +
            "WHERE id=#{id}")
    int update(Teacher teacher);

    @Select("SELECT * FROM teacher WHERE id=#{id}")
    Teacher findOne(@Param("id") Integer id);

    @Select("SELECT * FROM teacher")
    List<Teacher> findAll();

    @Select("SELECT * FROM teacher WHERE username=#{username}")
    Teacher findOneByUsername(@Param("username") String username);
}
