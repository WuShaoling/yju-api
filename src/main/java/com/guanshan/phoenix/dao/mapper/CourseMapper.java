package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.webdomain.response.ResHotCourseList;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    @Delete({
        "delete from course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into course (id, teacher_id, ",
        "name, description)",
        "values (#{id,jdbcType=INTEGER}, #{teacherId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(Course record);

    @InsertProvider(type=CourseSqlProvider.class, method="insertSelective")
    int insertSelective(Course record);

    @Select({
        "select",
        "id, teacher_id, name, description",
        "from course",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="teacher_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Course selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Course record);

    @Update({
        "update course",
        "set teacher_id = #{teacherId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Course record);

    @Select({
            "select c.id courseId, c.name courseName, c.description courseDes,",
            "t.user_id teacherId, t.name teacherName, t.email teacherContact",
            "from course c",
            "inner join teacher t on c.teacher_id = t.user_id",
    })
    List<Map> getAllCourses();

    @Select({
            "select exists (select 1 from course",
            "where teacher_id=#{teacherId, jdbcType=INTEGER})"
    })
    boolean isTeacherUsedByCourse(int teacherId);

    @Select({
            "select c.id, ifnull(course_actual_student.actual_student_num, 0) actual_student_num, ",
                    "ifnull(course_total_student.total_student_num, 0) total_student_num, c.name course_name, c.description, r.url, t.name teacher_name, t.email",
                    "from course c",
                          "inner join course_resource cr on cr.course_id = c.id and type = 1",
                          "inner join resource r on r.id = cr.resource_id",
                          "inner join teacher t on t.user_id = c.teacher_id",
                          "left join",
                            "(select course_id, count(*) as actual_student_num",
                             "from class c inner join student_class sc on sc.class_id  = c.id",
                             "group by course_id) as course_actual_student on course_actual_student.course_id=c.id",
                          "left join",
                            "(select course_id, sum(student_num) as total_student_num",
                             "from class",
                             "group by course_id",
                            ") as course_total_student on course_actual_student.course_id = course_total_student.course_id",
                    "order by actual_student_num desc limit 8"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="actual_student_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="total_student_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="course_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="url", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="teacher_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
    })
    List<ResHotCourseList.ResHotCourseDetail> getHotCourses();

    @Select({
            "select count(*) from course"
    })
    int getCount();
}