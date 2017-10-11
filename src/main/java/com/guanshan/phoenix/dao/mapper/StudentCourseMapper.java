package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentCourse;
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

public interface StudentCourseMapper {
    @Delete({
        "delete from student_course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into student_course (id, student_id, ",
        "course_id)",
        "values (#{id,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, ",
        "#{courseId,jdbcType=INTEGER})"
    })
    int insert(StudentCourse record);

    @InsertProvider(type=StudentCourseSqlProvider.class, method="insertSelective")
    int insertSelective(StudentCourse record);

    @Select({
        "select",
        "id, student_id, course_id",
        "from student_course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    StudentCourse selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StudentCourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentCourse record);

    @Update({
        "update student_course",
        "set student_id = #{studentId,jdbcType=INTEGER},",
          "course_id = #{courseId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StudentCourse record);

    @Select("SELECT course_id from student_course WHERE studentId=#{id,jdbcType=INTEGER}")
    List<Integer> selectCourseIdsByStudentId(Integer id);
}