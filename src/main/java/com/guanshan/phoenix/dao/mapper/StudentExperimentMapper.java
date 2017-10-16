package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentExperiment;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

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
}