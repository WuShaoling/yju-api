package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Homework;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface HomeworkMapper {
    @Delete({
        "delete from homework",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @InsertProvider(type=HomeworkSqlProvider.class, method="insertSelective")
    int insertSelective(Homework record);

    @Select({
        "select",
        "hw.id id, hw.module_id, hw.name, hw.description, hw.publish_date, hw.deadline_date, ",
        "hw.class_id, ei.image_type, ei.image_name, ei.image_version, hw.homework_content",
        "from homework hw inner join experiment_image ei on hw.image_id = ei.id",
        "where hw.id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="image_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="image_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="image_version", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="homework_content", javaType=String.class, jdbcType=JdbcType.LONGVARCHAR)
    })
    Homework selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HomeworkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Homework record);

    @Select({
            "select hw.id id, hw.module_id, hw.name, hw.description, hw.publish_date, hw.deadline_date, hw.class_id,",
            "ei.image_type, ei.image_name, ei.image_version",
            "from homework hw inner join experiment_image ei on hw.image_id = ei.id",
            "where module_id = #{moduleId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="image_version", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Homework> selectByModuleId(Integer moduleId);

    @Select({
            "select hw.id id, hw.module_id, hw.name, hw.description, hw.publish_date, hw.deadline_date, hw.class_id,",
            "ei.image_type, ei.image_name, ei.image_version",
            "from homework hw inner join experiment_image ei on hw.image_id = ei.id",
            "where module_id = #{moduleId,jdbcType=INTEGER} and class_id = #{classId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="image_version", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Homework> selectByModuleIdAndClassId(@Param("moduleId") Integer moduleId, @Param("classId")Integer classId);

    @Select({
            "select exists (select 1 from homework",
            "where class_id=#{classId, jdbcType=INTEGER})"
    })
    boolean isClassUsedByHomework(int classId);

    @Select({
            "select exists (select 1 from homework",
            "where module_id=#{moduleId, jdbcType=INTEGER})"
    })
    boolean isModuleUsedByHomework(int moduleId);

    @Select({
            "select hw.id id, hw.module_id, hw.name, hw.description, hw.publish_date, hw.deadline_date, hw.class_id,",
            "ei.image_type, ei.image_name, ei.image_version",
            "from homework hw inner join experiment_image ei on hw.image_id = ei.id",
            "where class_id = #{classId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publish_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="deadline_date", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_type", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="image_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="image_version", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Homework> selectByClassId(int classId);

    @Select({
            "select m.id moduleId, m.name moduleName,  hw.id homeworkId, hw.name homeworkName, ei.image_type imageType,",
            "hw.description homeworkDes, hw.deadline_date dueDate,",
            "hw.publish_date publishDate, sh.id studentHomeworkId, sh.submission_date submissionDate",
            "from homework hw",
            "inner join experiment_image ei on ei.id = hw.image_id",
            "inner join module m on hw.module_id = m.id",
            "left join student_homework sh on hw.id = sh.homework_id and sh.student_id = #{studentId, jdbcType=INTEGER}",
            "where hw.class_id = #{classId, jdbcType=INTEGER}",
            "order by moduleId",
    })
    List<Map> selectHomeworkDetailByClassAndStudentId(@Param("classId")Integer classId, @Param("studentId") Integer studentId);

    @Select({
            "select c.id classId, c.name className, hw.name homeworkName, hw.description,",
            "hw.publish_date publishDate, hw.deadline_date deadlineDate",
            "from homework hw",
            "inner join class c on hw.class_id = c.id",
            "inner join teacher t on c.teacher_id = t.user_id",
            "where t.user_id=#{teacherId, jdbcType=INTEGER}",
            "order by publishDate desc"
    })
    List<Map> selectHomeworkDetailByTeacherId(int teacherId);

    @Select({
            "select m.id moduleId, m.name moduleName,  hw.id homeworkId, hw.name homeworkName,",
            "hw.description homeworkDes, hw.deadline_date dueDate,",
            "hw.publish_date publishDate",
            "from module m",
            "left join homework hw on hw.module_id = m.id and hw.class_id=#{classId, jdbcType=INTEGER}",
            "where m.course_id = #{courseId, jdbcType=INTEGER}",
            "order by moduleId",
    })
    List<Map> selectHomeworkListByCourseAndClassId(@Param("courseId")Integer courseId, @Param("classId")Integer classId);
}