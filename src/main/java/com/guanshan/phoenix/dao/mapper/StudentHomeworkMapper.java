package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentHomework;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface StudentHomeworkMapper {
    @Delete({
        "delete from student_homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into student_homework (id, student_id, ",
        "homework_id, period_id, ",
        "score)",
        "values (#{id,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, ",
        "#{homeworkId,jdbcType=INTEGER}, #{periodId,jdbcType=INTEGER}, ",
        "#{score,jdbcType=INTEGER})"
    })
    int insert(StudentHomework record);

    @InsertProvider(type=StudentHomeworkSqlProvider.class, method="insertSelective")
    int insertSelective(StudentHomework record);

    @Select({
        "select",
        "id, student_id, homework_id, period_id, score",
        "from student_homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="homework_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="period_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="score", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    StudentHomework selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StudentHomeworkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentHomework record);

    @Update({
        "update student_homework",
        "set student_id = #{studentId,jdbcType=INTEGER},",
          "homework_id = #{homeworkId,jdbcType=INTEGER},",
          "period_id = #{periodId,jdbcType=INTEGER},",
          "score = #{score,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StudentHomework record);
}