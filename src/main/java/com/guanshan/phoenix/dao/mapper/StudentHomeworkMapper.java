package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentHomework;
import java.util.Date;

import com.guanshan.phoenix.enums.ResourceTypeEnum;
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
        "homework_id, cloudware_id, ",
        "comment, score, ",
        "submission_date, lastEdit_date)",
        "values (#{id,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, ",
        "#{homeworkId,jdbcType=INTEGER}, #{cloudwareId,jdbcType=INTEGER}, ",
        "#{comment,jdbcType=VARCHAR}, #{score,jdbcType=INTEGER}, ",
        "#{submissionDate,jdbcType=TIMESTAMP}, #{lastEditDate,jdbcType=TIMESTAMP})"
    })
    int insert(StudentHomework record);

    @InsertProvider(type=StudentHomeworkSqlProvider.class, method="insertSelective")
    int insertSelective(StudentHomework record);

    @Select({
        "select",
        "sh.id, sh.student_id, sh.homework_id, sh.cloudware_id, sh.comment, sh.score, sh.submission_date, ",
        "sh.lastEdit_date, cw.web_socket, r.url",
        "from student_homework sh inner join cloudware cw on cw.id=cloudware_id",
                                 "left out join student_homework_resource shr on shr.student_homework_id = sh.id",
                                                                                "and shr.type = 1",
                                 "inner join resource r on r.id = shr.resource_id",
        "where sh.id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="homework_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="cloudware_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="score", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="submission_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="lastEdit_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="web_socket", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="url", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    StudentHomework selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StudentHomeworkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentHomework record);

    @Update({
        "update student_homework",
        "set student_id = #{studentId,jdbcType=INTEGER},",
          "homework_id = #{homeworkId,jdbcType=INTEGER},",
          "cloudware_id = #{cloudwareId,jdbcType=INTEGER},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=INTEGER},",
          "submission_date = #{submissionDate,jdbcType=TIMESTAMP},",
          "lastEdit_date = #{lastEditDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StudentHomework record);

    @Select({
            "select",
            "sh.id, sh.student_id, sh.homework_id, sh.cloudware_id, sh.comment, sh.score, sh.submission_date, ",
            "sh.lastEdit_date, cw.web_socket, r.url",
            "from student_homework sh inner join cloudware cw on cw.id=cloudware_id",
            "left out join student_homework_resource shr on shr.student_homework_id = sh.id",
            "and shr.type = 1",
            "inner join resource r on r.id = shr.resource_id",
            "where sh.id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="homework_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="cloudware_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="score", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="submission_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="lastEdit_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="web_socket", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="url", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    StudentHomework selectByStudentIdAndHomeworkId(int studentId, int homeworkId);
}