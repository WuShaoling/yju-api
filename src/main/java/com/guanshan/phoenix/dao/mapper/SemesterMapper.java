package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Semester;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SemesterMapper {
    @Delete({
        "delete from semester",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into semester (id, year, ",
        "semester)",
        "values (#{id,jdbcType=INTEGER}, #{year,jdbcType=VARCHAR}, ",
        "#{semester,jdbcType=INTEGER})"
    })
    int insert(Semester record);

    @InsertProvider(type=SemesterSqlProvider.class, method="insertSelective")
    int insertSelective(Semester record);

    @Select({
        "select",
        "id, year, semester",
        "from semester",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="year", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="semester", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Semester selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SemesterSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Semester record);

    @Update({
        "update semester",
        "set year = #{year,jdbcType=VARCHAR},",
          "semester = #{semester,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Semester record);
}