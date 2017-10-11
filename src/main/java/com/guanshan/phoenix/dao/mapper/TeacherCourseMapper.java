package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.TeacherCourse;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TeacherCourseMapper {
    @Delete({
        "delete from teacher_course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into teacher_course (id, teacher_id, ",
        "course_id)",
        "values (#{id,jdbcType=INTEGER}, #{teacherId,jdbcType=INTEGER}, ",
        "#{courseId,jdbcType=INTEGER})"
    })
    int insert(TeacherCourse record);

    @InsertProvider(type=TeacherCourseSqlProvider.class, method="insertSelective")
    int insertSelective(TeacherCourse record);

    @Select({
        "select",
        "id, teacher_id, course_id",
        "from teacher_course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="teacher_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    TeacherCourse selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TeacherCourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TeacherCourse record);

    @Update({
        "update teacher_course",
        "set teacher_id = #{teacherId,jdbcType=INTEGER},",
          "course_id = #{courseId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TeacherCourse record);
}