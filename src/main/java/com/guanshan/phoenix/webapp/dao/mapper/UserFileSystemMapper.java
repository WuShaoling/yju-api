package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.UserFileSystem;

@Mapper
public interface UserFileSystemMapper {

    @Insert("INSERT INTO user_file_system (status, personal_volumn, remark, user_id, id) VALUES (#{status}, #{personalVolumn}, #{remark}, #{userId}, #{id})")
    int insert(UserFileSystem user_file_system);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE user_file_system SET status=#{status}, personal_volumn=#{personalVolumn}, remark=#{remark}, user_id=#{userId}, id=#{id} WHERE id=#{id}")
    int update(UserFileSystem user_file_system);

    @Select("SELECT * FROM user_file_system WHERE id=#{id}")
    UserFileSystem findOne(@Param("id") Integer id);

}