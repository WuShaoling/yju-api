package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Navlist;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface NavlistMapper {
    @Delete({
        "delete from navlist",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into navlist (id, name, ",
        "url, class)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{uiClass,jdbcType=VARCHAR})"
    })
    int insert(Navlist record);

    @InsertProvider(type=NavlistSqlProvider.class, method="insertSelective")
    int insertSelective(Navlist record);

    @Select({
        "select",
        "id, name, url, class",
        "from navlist",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="url", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="class", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Navlist selectByPrimaryKey(Integer id);

    @UpdateProvider(type=NavlistSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Navlist record);

    @Update({
        "update navlist",
        "set name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "class = #{uiClass,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Navlist record);
}