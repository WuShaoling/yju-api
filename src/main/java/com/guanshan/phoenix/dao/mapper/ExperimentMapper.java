package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Experiment;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ExperimentMapper {
    @Delete({
        "delete from experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into experiment (id, period_id, ",
        "name, description, ",
        "cloudware_id, publish_date, ",
        "expire_date)",
        "values (#{id,jdbcType=INTEGER}, #{periodId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{cloudwareId,jdbcType=VARCHAR}, #{publishDate,jdbcType=DATE}, ",
        "#{expireDate,jdbcType=DATE})"
    })
    int insert(Experiment record);

    @InsertProvider(type=ExperimentSqlProvider.class, method="insertSelective")
    int insertSelective(Experiment record);

    @Select({
        "select",
        "id, period_id, name, description, cloudware_id, publish_date, expire_date",
        "from experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="period_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cloudware_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="expire_date", javaType=Date.class, jdbcType=JdbcType.DATE)
    })
    Experiment selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, period_id, name, description, cloudware_id, publish_date, expire_date",
            "from experiment",
            "where period_id = #{periodId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Arg(column = "period_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Arg(column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Arg(column = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Arg(column = "cloudware_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Arg(column = "publish_date", javaType = Date.class, jdbcType = JdbcType.DATE),
            @Arg(column = "expire_date", javaType = Date.class, jdbcType = JdbcType.DATE)
    })
    List<Experiment> selectByPeriodId(Integer periodId);

    @UpdateProvider(type=ExperimentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Experiment record);

    @Update({
        "update experiment",
        "set period_id = #{periodId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "cloudware_id = #{cloudwareId,jdbcType=VARCHAR},",
          "publish_date = #{publishDate,jdbcType=DATE},",
          "expire_date = #{expireDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Experiment record);
}