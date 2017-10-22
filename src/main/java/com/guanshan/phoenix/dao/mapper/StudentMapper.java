package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface StudentMapper {
    @Delete({
        "delete from student",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into student (id, user_id, ",
        "sno, name, gender, ",
        "birthday)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{sno,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{gender,jdbcType=INTEGER}, ",
        "#{birthday,jdbcType=VARCHAR})"
    })
    int insert(Student record);

    @InsertProvider(type=StudentSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insertSelective(Student record);

    @Select({
        "select",
        "id, user_id, sno, name, gender, birthday",
        "from student",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="sno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="birthday", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Student selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StudentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Student record);

    @Update({
        "update student",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "sno = #{sno,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=INTEGER},",
          "birthday = #{birthday,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Student record);

    @Update({
            "update student",
            "set sno = #{sno,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=INTEGER},",
            "birthday = #{birthday,jdbcType=VARCHAR}",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByUserId(Student record);

    @Select({
            "select",
            "s.id, s.user_id, s.sno, s.name, s.gender, s.birthday",
            "from student s",
                "inner join student_class sc on sc.student_id = s.user_id",
            "where sc.class_id = #{classId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="sno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="birthday", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Student> selectByClassId(int classId);

    @Select({
            "select",
            "id, user_id, sno, name, gender, birthday",
            "from student",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="sno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="birthday", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Student selectByUserId(Integer userId);
}