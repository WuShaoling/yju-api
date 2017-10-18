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
        "deadline_date)",
        "values (#{id,jdbcType=INTEGER}, #{moduleId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{cloudwareType,jdbcType=INTEGER}, #{publishDate,jdbcType=DATE}, ",
        "#{deadlineDate,jdbcType=DATE})"
    })
    int insert(Experiment record);

    @InsertProvider(type=ExperimentSqlProvider.class, method="insertSelective")
    int insertSelective(Experiment record);

    @Select({
        "select",
        "id, module_id, name, description, cloudware_type, publish_date, deadline_date",
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
        @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.DATE)
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
          "deadline_date = #{deadlineDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Experiment record);

    @Select({
            "select *",
            "from experiment",
            "where module_id = #{moduleId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cloudware_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Experiment> selectByModuleId(Integer moduleId);
}