package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Course;
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

public interface CourseMapper {
    @Delete({
        "delete from course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into course (id, semester_id, ",
        "course_sid, name, ",
        "image, description, ",
        "date, duration, student_number)",
        "values (#{id,jdbcType=INTEGER}, #{semesterId,jdbcType=INTEGER}, ",
        "#{courseSid,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{image,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{date,jdbcType=DATE}, #{duration,jdbcType=VARCHAR}, #{studentNumber,jdbcType=INTEGER})"
    })
    int insert(Course record);

    @InsertProvider(type=CourseSqlProvider.class, method="insertSelective")
    int insertSelective(Course record);

    @Select({
        "select",
        "id, semester_id, course_sid, name, image, description, date, duration, student_number",
        "from course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="semester_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="course_sid", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="image", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="date", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="duration", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="student_number", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Course selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Course record);

    @Update({
        "update course",
        "set semester_id = #{semesterId,jdbcType=INTEGER},",
          "course_sid = #{courseSid,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "image = #{image,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "date = #{date,jdbcType=DATE},",
          "duration = #{duration,jdbcType=VARCHAR},",
          "student_number = #{studentNumber,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Course record);
}