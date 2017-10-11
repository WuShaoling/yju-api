package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Teacher;
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

public interface TeacherMapper {
    @Delete({
        "delete from teacher",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into teacher (id, user_id, ",
        "tno, name, gender, ",
        "brithday, title, email, ",
        "phone)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{tno,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{gender,jdbcType=INTEGER}, ",
        "#{brithday,jdbcType=DATE}, #{title,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR})"
    })
    int insert(Teacher record);

    @InsertProvider(type=TeacherSqlProvider.class, method="insertSelective")
    int insertSelective(Teacher record);

    @Select({
        "select",
        "id, user_id, tno, name, gender, brithday, title, email, phone",
        "from teacher",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="tno", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="gender", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="brithday", javaType=Date.class, jdbcType=JdbcType.DATE),
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
          "brithday = #{brithday,jdbcType=DATE},",
          "title = #{title,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Teacher record);
}