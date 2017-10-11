package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.User;
import java.util.Date;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (id, username, ",
        "password, role, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{role,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=DATE}, #{updateTime,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, username, password, role, create_time, update_time",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="role", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="update_time", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=DATE},",
          "update_time = #{updateTime,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Select({
        "select",
        "id, username, password, role, create_time, update_time",
        "from user",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    User selectByUsername(String username); // rain
}