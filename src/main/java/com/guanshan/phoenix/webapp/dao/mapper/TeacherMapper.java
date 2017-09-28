package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Insert("INSERT INTO teacher (user_id, name, title, gender, contact, birth, tno, id) VALUES (#{user_id}, #{name}, #{title}, #{gender}, #{contact}, #{birth}, #{tno}, #{id})")
    int insert(Teacher teacher);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE teacher SET user_id=#{user_id}, name=#{name}, title=#{title}, gender=#{gender}, contact=#{contact}, birth=#{birth}, tno=#{tno}, id=#{id} WHERE id=#{id}")
    int update(Teacher teacher);

    @Select("SELECT * FROM teacher WHERE id=#{id}")
    Teacher findOne(@Param("id") Integer id);

    @Select("SELECT * FROM teacher")
    List<Teacher> findAll();

}