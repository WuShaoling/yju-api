package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Term;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TermMapper {
    @Delete({
        "delete from term",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into term (id, year, ",
        "semester)",
        "values (#{id,jdbcType=INTEGER}, #{year,jdbcType=VARCHAR}, ",
        "#{semester,jdbcType=INTEGER})"
    })
    int insert(Term record);

    @InsertProvider(type=TermSqlProvider.class, method="insertSelective")
    int insertSelective(Term record);

    @Select({
        "select",
        "id, year, semester",
        "from term",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="year", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="semester", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Term selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TermSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Term record);

    @Update({
        "update term",
        "set year = #{year,jdbcType=VARCHAR},",
          "semester = #{semester,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Term record);

    @Select({
            "select",
            "id, year, semester",
            "from term"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="year", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="semester", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Term> getAllTerms();

    @Select({
            "select",
            "id, year, semester",
            "from term",
            "where year = #{year,jdbcType=VARCHAR} and semester = #{semester,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="year", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="semester", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Term selectByYearAndSemester(@Param("year") String year, @Param("semester") Integer semester);
}