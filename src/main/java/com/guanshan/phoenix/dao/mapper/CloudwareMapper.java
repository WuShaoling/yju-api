package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Cloudware;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface CloudwareMapper {
    @Delete({
        "delete from cloudware",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cloudware (id, web_socket, ",
        "service_id, instance_id)",
        "values (#{id,jdbcType=INTEGER}, #{webSocket,jdbcType=VARCHAR}, ",
        "#{serviceId,jdbcType=VARCHAR}, #{instanceId,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(Cloudware record);

    @InsertProvider(type=CloudwareSqlProvider.class, method="insertSelective")
    int insertSelective(Cloudware record);

    @Select({
        "select",
        "id, web_socket, service_id, instance_id",
        "from cloudware",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="web_socket", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="service_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="instance_id", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Cloudware selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CloudwareSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Cloudware record);

    @Update({
        "update cloudware",
        "set web_socket = #{webSocket,jdbcType=VARCHAR},",
          "service_id = #{serviceId,jdbcType=VARCHAR},",
          "instance_id = #{instanceId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Cloudware record);
}