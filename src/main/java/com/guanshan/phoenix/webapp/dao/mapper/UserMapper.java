package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.User;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (username, update_time, password, role, craete_time, id) VALUES (#{username}, #{updateTime}, #{password}, #{role}, #{craeteTime}, #{id})")
    int insert(User user);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE user SET username=#{username}, update_time=#{updateTime}, password=#{password}, role=#{role}, craete_time=#{craeteTime}, id=#{id} WHERE id=#{id}")
    int update(User user);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findOne(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE username=#{username}")
    User findOneByUsername(@Param("username") String username);

}