package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Homework;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface HomeworkMapper {
    @Delete({
        "delete from homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into homework (id, module_id, ",
        "name, description, ",
        "cloudware_type, publish_date, ",
        "deadline_date, class_id)",
        "values (#{id,jdbcType=INTEGER}, #{moduleId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{cloudwareType,jdbcType=INTEGER}, #{publishDate,jdbcType=TIMESTAMP}, ",
        "#{deadlineDate,jdbcType=TIMESTAMP}, #{classId,jdbcType=INTEGER})"
    })
    int insert(Homework record);

    @InsertProvider(type=HomeworkSqlProvider.class, method="insertSelective")
    int insertSelective(Homework record);

    @Select({
        "select",
        "id, module_id, name, description, cloudware_type, publish_date, deadline_date, ",
        "class_id",
        "from homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Homework selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HomeworkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Homework record);

    @Update({
        "update homework",
        "set module_id = #{moduleId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "cloudware_type = #{cloudwareType,jdbcType=INTEGER},",
          "publish_date = #{publishDate,jdbcType=TIMESTAMP},",
          "deadline_date = #{deadlineDate,jdbcType=TIMESTAMP},",
          "class_id = #{classId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Homework record);

    @Select({
            "select *",
            "from homework",
            "where module_id = #{moduleId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Homework> selectByModuleId(Integer moduleId);

    @Select({
            "select *",
            "from homework",
            "where module_id = #{moduleId,jdbcType=INTEGER} and class_id = #{classId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Homework> selectByModuleIdAndClassId(@Param("moduleId") Integer moduleId, @Param("classId")Integer classId);

    @Select({
            "select exists (select 1 from homework",
            "where class_id=#{classId, jdbcType=INTEGER})"
    })
    boolean isClassUsedByHomework(int classId);

    @Select({
            "select exists (select 1 from homework",
            "where module_id=#{moduleId, jdbcType=INTEGER})"
    })
    boolean isModuleUsedByHomework(int moduleId);

}