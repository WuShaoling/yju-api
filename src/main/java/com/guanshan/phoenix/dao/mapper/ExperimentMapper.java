package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Experiment;
import java.util.Date;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ExperimentMapper {
    @Delete({
        "delete from experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into experiment (id, module_id, ",
        "name, description, ",
        "image_id, publish_date, ",
        "deadline_date, experiment_content)",
        "values (#{id,jdbcType=INTEGER}, #{moduleId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{imageId,jdbcType=INTEGER}, #{publishDate,jdbcType=TIMESTAMP}, ",
        "#{deadlineDate,jdbcType=TIMESTAMP}, #{experimentContent,jdbcType=LONGVARCHAR})"
    })
    int insert(Experiment record);

    @InsertProvider(type=ExperimentSqlProvider.class, method="insertSelective")
    int insertSelective(Experiment record);

    @Select({
        "select",
        "experiment.id, module_id, name, description, publish_date, deadline_date, ",
        "experiment_content, image_type, image_name, image_version",
        "from experiment inner join experiment_image on experiment.image_id = experiment_image.id",
        "where experiment.id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="image_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="image_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="image_version", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="experiment_content", javaType=String.class, jdbcType=JdbcType.LONGVARCHAR)
    })
    Experiment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ExperimentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Experiment record);

    @Select({
            "select",
            "e.id, e.module_id, e.name, e.description, e.publish_date, e.deadline_date",
            "ei.image_type, ei.image_name, ei.image_version",
            "from experiment e inner join experiment_image ei on e.image_id = ei.id",
            "where module_id = #{moduleId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="image_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="image_version", javaType=String.class, jdbcType=JdbcType.VARCHAR),

    })
    List<Experiment> selectByModuleId(Integer moduleId);

    @Select({
            "select id from experiment_image",
            "where image_type = #{imageType, jdbcType=INTEGER} and CONCAT(image_name, ':', image_version) = #{imageNameVersion, jdbcType=VARCHAR}"
    })
    Integer selectImageIdByTypeAndNameVersion(@Param("imageType") int imageType, @Param("imageNameVersion") String imageNameVersion);

    @Select({
            "select count(*) from experiment"
    })
    int getCount();
}