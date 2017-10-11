package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Homework;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HomeworkMapper {
    @Delete({
        "delete from homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into homework (id, period_id, ",
        "name, description)",
        "values (#{id,jdbcType=INTEGER}, #{periodId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    int insert(Homework record);

    @InsertProvider(type=HomeworkSqlProvider.class, method="insertSelective")
    int insertSelective(Homework record);

    @Select({
        "select",
        "id, period_id, name, description",
        "from homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="period_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Homework selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HomeworkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Homework record);

    @Update({
        "update homework",
        "set period_id = #{periodId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Homework record);
}