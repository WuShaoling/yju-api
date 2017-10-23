package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Experiment;
import java.util.Date;
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

public interface ExperimentMapper {
    @Delete({
        "delete from experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into experiment (id, module_id, ",
        "name, description, ",
        "cloudware_type, publish_date, ",
        "deadline_date, experiment_content)",
        "values (#{id,jdbcType=INTEGER}, #{moduleId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{cloudwareType,jdbcType=INTEGER}, #{publishDate,jdbcType=DATE}, ",
        "#{deadlineDate,jdbcType=DATE}, #{experimentContent,jdbcType=LONGVARCHAR})"
    })
    int insert(Experiment record);

    @InsertProvider(type=ExperimentSqlProvider.class, method="insertSelective")
    int insertSelective(Experiment record);

    @Select({
        "select",
        "id, module_id, name, description, cloudware_type, publish_date, deadline_date, ",
        "experiment_content",
        "from experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="experiment_content", javaType=String.class, jdbcType=JdbcType.LONGVARCHAR)
    })
    Experiment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ExperimentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Experiment record);

    @Update({
        "update experiment",
        "set module_id = #{moduleId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "cloudware_type = #{cloudwareType,jdbcType=INTEGER},",
          "publish_date = #{publishDate,jdbcType=DATE},",
          "deadline_date = #{deadlineDate,jdbcType=DATE},",
          "experiment_content = #{experimentContent,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Experiment record);

    @Update({
        "update experiment",
        "set module_id = #{moduleId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "cloudware_type = #{cloudwareType,jdbcType=INTEGER},",
          "publish_date = #{publishDate,jdbcType=DATE},",
          "deadline_date = #{deadlineDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Experiment record);

    @Select({
            "select",
            "id, module_id, name, description, cloudware_type, publish_date, deadline_date",
            "from experiment",
            "where module_id = #{moduleId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.DATE),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.DATE)
    })
    List<Experiment> selectByModuleId(Integer moduleId);

    @Select({
            "select",
            "e.id, e.module_id, e.name, e.description, e.cloudware_type, e.publish_date, e.deadline_date",
            "from experiment e inner join module m on e.module_id = m.id",
            "where m.course_id = #{courseId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.DATE),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.DATE)
    })
    List<Experiment> selectByCourseId(Integer courseId);

    @Select({
            "select exists (select 1 from experiment",
            "where module_id=#{moduleId, jdbcType=INTEGER})"
    })
    boolean isModuleUsedByExperiment(int moduleId);

    @Select({
            "select exists (select 1 from student_experiment",
            "where experiment_id=#{experimentId, jdbcType=INTEGER})"
    })
    boolean isExperimentUsedByStudentExperiment(int experimentId);
}