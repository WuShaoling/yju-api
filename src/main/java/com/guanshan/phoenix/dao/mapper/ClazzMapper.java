package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Clazz;
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

public interface ClazzMapper {
    @Delete({
        "delete from class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into class (id, term_id, ",
        "course_id, date, duration, ",
        "student_num)",
        "values (#{id,jdbcType=INTEGER}, #{termId,jdbcType=INTEGER}, ",
        "#{courseId,jdbcType=INTEGER}, #{date,jdbcType=DATE}, #{duration,jdbcType=VARCHAR}, ",
        "#{studentNum,jdbcType=INTEGER})"
    })
    int insert(Clazz record);

    @InsertProvider(type=ClazzSqlProvider.class, method="insertSelective")
    int insertSelective(Clazz record);

    @Select({
        "select",
        "id, term_id, course_id, date, duration, student_num",
        "from class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="term_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="date", javaType=Date.class, jdbcType=JdbcType.DATE),
        @Arg(column="duration", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="student_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
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
          "student_num = #{studentNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Clazz record);
}