package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentExperiment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface StudentExperimentMapper {
    @Delete({
        "delete from student_experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into student_experiment (id, student_id, ",
        "experiment_id, cloudware_id)",
        "values (#{id,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, ",
        "#{experimentId,jdbcType=INTEGER}, #{cloudwareId,jdbcType=INTEGER})"
    })
    int insert(StudentExperiment record);

    @InsertProvider(type=StudentExperimentSqlProvider.class, method="insertSelective")
    int insertSelective(StudentExperiment record);

    @Select({
        "select",
        "id, student_id, experiment_id, cloudware_id",
        "from student_experiment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="experiment_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="cloudware_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    StudentExperiment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StudentExperimentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentExperiment record);

    @Update({
        "update student_experiment",
        "set student_id = #{studentId,jdbcType=INTEGER},",
          "experiment_id = #{experimentId,jdbcType=INTEGER},",
          "cloudware_id = #{cloudwareId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StudentExperiment record);


    @Select("SELECT cloudware_id FROM student_experiment WHERE student_id=#{studentId} AND experiment_id=#{experimentId}")
    int selectCloudwareIdByStudentIdAndExperimentId(@Param("studentId") Integer studentId, @Param("experimentId") Integer experimentId);

    @Select({"SELECT *",
            "FROM student_experiment",
            "WHERE student_id=#{studentId} AND experiment_id=#{experimentId}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="experiment_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="cloudware_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    StudentExperiment selectByStudentIdAndExperimentId(@Param("studentId") Integer studentId, @Param("experimentId") Integer experimentId);

    @Select({"SELECT id",
            "FROM student_experiment",
            "WHERE experiment_id=#{experimentId}"
    })
    List<Integer> selectStudentExperimentByExperimentId(Integer experimentId);
}