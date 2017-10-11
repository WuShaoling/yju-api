package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Period;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PeriodMapper {
    @Delete({
        "delete from period",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into period (id, course_id, ",
        "name, description)",
        "values (#{id,jdbcType=INTEGER}, #{courseId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    int insert(Period record);

    @InsertProvider(type=PeriodSqlProvider.class, method="insertSelective")
    int insertSelective(Period record);

    @Select({
        "select",
        "id, course_id, name, description",
        "from period",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Period selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PeriodSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Period record);

    @Update({
        "update period",
        "set course_id = #{courseId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Period record);
}