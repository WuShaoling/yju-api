package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (username, update_time, create_time, role, password, id) VALUES (#{username}, #{update_time}, #{create_time}, #{role}, #{password}, #{id})")
    int insert(User user);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE user SET username=#{username}, update_time=#{update_time}, create_time=#{create_time}, role=#{role}, password=#{password}, id=#{id} WHERE id=#{id}")
    int update(User user);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findOne(@Param("id") Integer id);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE username=#{username}")
    User findOneByUsername(@Param("username") String username);

}