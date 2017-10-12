package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Clazz;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ClazzMapper {
    @Delete({
        "delete from class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into class (id, semester_id, ",
        "course_id, name, ",
        "studnet_num)",
        "values (#{id,jdbcType=INTEGER}, #{semesterId,jdbcType=INTEGER}, ",
        "#{courseId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{studnetNum,jdbcType=INTEGER})"
    })
    int insert(Clazz record);

    @InsertProvider(type=ClazzSqlProvider.class, method="insertSelective")
    int insertSelective(Clazz record);

    @Select({
        "select",
        "id, semester_id, course_id, name, studnet_num",
        "from class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="semester_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="studnet_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Clazz selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ClazzSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Clazz record);

    @Update({
        "update class",
        "set semester_id = #{semesterId,jdbcType=INTEGER},",
          "course_id = #{courseId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "studnet_num = #{studnetNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Clazz record);

    /*----- customer start -----*/
    @Select("SELECT * FROM class WHERE course_id=#{courseId,jdbcType=INTEGER")
    List<Clazz> selectClazzByCourseId(Integer courseId);
}