package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface InstructorMapper {

    @Insert("INSERT INTO instructor (name, username, password), " +
            "VALUES (#{name}, #{username}, #{password}")
    int insert(Instructor instructor);

    @Delete("DELETE FROM instructor WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE instructor SET name=#{name}, username=#{username}, password=#{password} " +
            "WHERE id=#{id}")
    int update(Instructor instructor);

    @Select("SELECT * FROM instructor WHERE id=#{id}")
    Instructor findOne(@Param("id") Integer id);

    @Select("SELECT * FROM instructor")
    List<Instructor> findAll();

    @Select("SELECT * FROM instructor WHERE username=#{username}")
    Instructor findOneUsername(@Param("username") String username);
}
