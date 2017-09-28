package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO student (user_id, name, birth, gender, sno, id) VALUES (#{user_id}, #{name}, #{birth}, #{gender}, #{sno}, #{id})")
    int insert(Student student);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student SET user_id=#{user_id}, name=#{name}, birth=#{birth}, gender=#{gender}, sno=#{sno}, id=#{id} WHERE id=#{id}")
    int update(Student student);

    @Select("SELECT * FROM student WHERE id=#{id}")
    Student findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student")
    List<Student> findAll();

}