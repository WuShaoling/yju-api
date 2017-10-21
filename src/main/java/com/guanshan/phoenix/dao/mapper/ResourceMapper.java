package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Resource;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ResourceMapper {
    @Delete({
        "delete from resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into resource (id, name, ",
        "url, width, height)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{width,jdbcType=VARCHAR}, #{height,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(Resource record);

    @InsertProvider(type=ResourceSqlProvider.class, method="insertSelective")
    int insertSelective(Resource record);

    @Select({
        "select",
        "id, name, url, width, height",
        "from resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="url", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="width", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="height", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Resource selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Resource record);

    @Update({
        "update resource",
        "set name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "width = #{width,jdbcType=VARCHAR},",
          "height = #{height,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Resource record);
}