package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.UserFileSystem;

import java.util.List;

@Mapper
public interface UserFileSystemMapper {

    @Insert("INSERT INTO user_file_system (status, remark, user_id, personal_volume, id) VALUES (#{status}, #{remark}, #{user_id}, #{personal_volume}, #{id})")
    int insert(UserFileSystem user_file_system);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE user_file_system SET status=#{status}, remark=#{remark}, user_id=#{user_id}, personal_volume=#{personal_volume}, id=#{id} WHERE id=#{id}")
    int update(UserFileSystem user_file_system);

    @Select("SELECT * FROM user_file_system WHERE id=#{id}")
    UserFileSystem findOne(@Param("id") Integer id);

    @Select("SELECT * FROM user_file_system")
    List<UserFileSystem> findAll();

}