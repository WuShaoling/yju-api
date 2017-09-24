package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO student (name, username, password), " +
            "VALUES (#{name}, #{username}, #{password}")
    int insert(Student student);

    @Delete("DELETE FROM student WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE student SET name=#{name}, username=#{username}, password=#{password} " +
            "WHERE id=#{id}")
    int update(Student student);

    @Select("SELECT * FROM student WHERE id=#{id}")
    Student findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student")
    List<Student> findAll();

    @Select("SELECT * FROM student WHERE username=#{username}")
    Student findOneByUsername(@Param("username") String username);
}
