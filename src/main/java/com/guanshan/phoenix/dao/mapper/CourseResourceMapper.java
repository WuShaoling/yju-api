package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.CourseResource;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CourseResourceMapper {
    @Delete({
        "delete from course_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into course_resource (id, course_id, ",
        "resource_id)",
        "values (#{id,jdbcType=INTEGER}, #{courseId,jdbcType=INTEGER}, ",
        "#{resourceId,jdbcType=INTEGER})"
    })
    int insert(CourseResource record);

    @InsertProvider(type=CourseResourceSqlProvider.class, method="insertSelective")
    int insertSelective(CourseResource record);

    @Select({
        "select",
        "id, course_id, resource_id",
        "from course_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="resource_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    CourseResource selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CourseResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CourseResource record);

    @Update({
        "update course_resource",
        "set course_id = #{courseId,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CourseResource record);
}