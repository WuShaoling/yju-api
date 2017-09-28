package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Instructor;

import java.util.List;

@Mapper
public interface InstructorMapper {

    @Insert("INSERT INTO instructor (contact, user_id, name, gender, tno, id) VALUES (#{contact}, #{user_id}, #{name}, #{gender}, #{tno}, #{id})")
    int insert(Instructor instructor);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE instructor SET contact=#{contact}, user_id=#{user_id}, name=#{name}, gender=#{gender}, tno=#{tno}, id=#{id} WHERE id=#{id}")
    int update(Instructor instructor);

    @Select("SELECT * FROM instructor WHERE id=#{id}")
    Instructor findOne(@Param("id") Integer id);

    @Select("SELECT * FROM instructor")
    List<Instructor> findAll();

}