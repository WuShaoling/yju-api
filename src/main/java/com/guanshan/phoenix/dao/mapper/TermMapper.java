package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Term;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

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
}