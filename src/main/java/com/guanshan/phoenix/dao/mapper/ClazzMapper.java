package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Clazz;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ClazzMapper {
    @Delete({
        "delete from class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into class (id, term_id, ",
        "course_id, date, duration, ",
        "student_num, name)",
        "values (#{id,jdbcType=INTEGER}, #{termId,jdbcType=INTEGER}, ",
        "#{courseId,jdbcType=INTEGER}, #{date,jdbcType=DATE}, #{duration,jdbcType=VARCHAR}, ",
        "#{studentNum,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
    })
    int insert(Clazz record);

    @InsertProvider(type=ClazzSqlProvider.class, method="insertSelective")
    int insertSelective(Clazz record);

    @Select({
        "select",
        "id, term_id, course_id, date, duration, student_num, name",
        "from class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="term_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="date", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="duration", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="student_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Clazz selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ClazzSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Clazz record);

    @Update({
        "update class",
        "set term_id = #{termId,jdbcType=INTEGER},",
          "course_id = #{courseId,jdbcType=INTEGER},",
          "date = #{date,jdbcType=DATE},",
          "duration = #{duration,jdbcType=VARCHAR},",
          "student_num = #{studentNum,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Clazz record);

    @Select({
            "select c.id classId, c.name className, tm.year, tm.semester, r.url, c.duration,",
            "c.student_num studentNum, cs.description, cs.id courseId, cs.name courseName,",
            "t.email, t.name teacherName, c.date classDate",
            "from class c",
            "inner join course cs on c.course_id = cs.id",
            "left join course_resource cr on cr.course_id = cs.id and cr.type = 1",
            "left join resource r on cr.resource_id = r.id",
            "inner join teacher t on cs.teacher_id = t.user_id",
            "inner join term tm on c.term_id = tm.id",
            "where t.user_id = #{teacherUserId,jdbcType=INTEGER}",
            "order by c.id",
    })
    List<Map> selectAllClassInfoByTeacherUserId(int teacherUserId);

    @Select({
            "select c.id classId, c.name className, tm.year, tm.semester, r.url, c.duration,",
            "ifnull(student_count.student_num,0) studentNum, cs.description, cs.id courseId, cs.name courseName,",
            "t.email, t.name teacherName, c.date classDate",
            "from class c",
            "left join (",
            "select class_id, count(*) student_num",
            "from student_class",
            "group by class_id",
            "        ) student_count on student_count.class_id = c.id",
            "inner join course cs on c.course_id = cs.id",
            "left join course_resource cr on cr.course_id = cs.id and cr.type = 1",
            "left join resource r on cr.resource_id = r.id",
            "inner join teacher t on cs.teacher_id = t.user_id",
            "inner join term tm on c.term_id = tm.id",
            "order by c.id"
    })
    List<Map> selectAllClassInfo();

    @Select({
            "select",
            "id, term_id, course_id, date, duration, student_num, name",
            "from class"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="term_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="date", javaType=Date.class, jdbcType=JdbcType.DATE),
            @Arg(column="duration", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="student_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Clazz> selectAll();

    @Select({
            "select exists (select 1 from class",
            "where term_id=#{termId, jdbcType=INTEGER})"
    })
    boolean isTermUsedByClass(int termId);

    @Select({
            "select exists (select 1 from class",
            "where course_id=#{courseId, jdbcType=INTEGER})"
    })
    boolean isCourseUsedByClass(int courseId);

    @Select({
            "select exists (select 1 from student_class",
            "where student_id=#{studentId, jdbcType=INTEGER} and class_id=#{classId, jdbcType=INTEGER})"
    })
    boolean isStudentInClass(@Param("studentId") Integer studentId, @Param("classId") Integer classId);

    @Select({
            "select",
            "id, term_id, course_id, date, duration, student_num, name",
            "from class",
            "where course_id = #{courseId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="term_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="date", javaType=Date.class, jdbcType=JdbcType.DATE),
            @Arg(column="duration", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="student_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Clazz> selectByCourseId(@Param("courseId") int courseId);

    @Select({
        "select count(*)",
        "from class",
        "where course_id = #{courseId,jdbcType=INTEGER}"
    })
    int getClassNumByCourseId(@Param("courseId") int courseId);
}