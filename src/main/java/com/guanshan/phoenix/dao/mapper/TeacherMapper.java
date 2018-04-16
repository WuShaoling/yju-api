package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TeacherMapper {
    @Delete({
        "delete from teacher",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from teacher",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByUserId(Integer userId);

    @Insert({
        "insert into teacher (id, user_id, ",
        "tno, name, gender, ",
        "title, email, phone)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{tno,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{gender,jdbcType=INTEGER}, ",
        "#{title,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR})"
    })
    int insert(Teacher record);

    @InsertProvider(type=TeacherSqlProvider.class, method="insertSelective")
    int insertSelective(Teacher record);

    @Select({
        "select",
        "id, user_id, tno, name, gender, title, email, phone",
        "from teacher",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="tno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="title", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Teacher selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TeacherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Teacher record);

    @Update({
        "update teacher",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "tno = #{tno,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=INTEGER},",
          "title = #{title,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Teacher record);

    @Update({
            "update teacher",
            "set tno = #{tno,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=INTEGER},",
            "title = #{title,jdbcType=INTEGER},",
            "email = #{email,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR}",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByUserId(Teacher record);

    @Select({
            "select",
            "id, user_id, tno, name, gender, title, email, phone",
            "from teacher",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="tno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="title", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Teacher selectByUserId(Integer userId);

    @Select({
            "select",
            "id, user_id, tno, name, gender, title, email, phone",
            "from teacher"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="tno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="title", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Teacher> getAllTeachers();

    @Select({
            "select",
            "t.id, t.user_id, t.tno, t.name, t.gender, t.title, t.email, t.phone",
            "from teacher t inner join class c on c.teacher_id=t.user_id",
            "where c.id = #{classId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="tno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="title", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Teacher selectByClassId(int classId);

    @Select({
            "select count(*) from teacher"
    })
    int getCount();
}