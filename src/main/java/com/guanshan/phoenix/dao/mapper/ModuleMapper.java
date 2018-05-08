package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.Module;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface ModuleMapper {
    @Delete({
        "delete from module",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into module (id, course_id, ",
        "name)",
        "values (#{id,jdbcType=INTEGER}, #{courseId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(Module record);

    @InsertProvider(type=ModuleSqlProvider.class, method="insertSelective")
    int insertSelective(Module record);

    @Select({
        "select",
        "id, course_id, name",
        "from module",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Module selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ModuleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Module record);

    @Update({
        "update module",
        "set course_id = #{courseId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Module record);

    @Select({
            "select *",
            "from module",
            "where course_id = #{courseId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="course_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Module> selectByCourseID(Integer courseId);

    @Select({
            "select m.id moduleId, m.name moduleName, e.id experimentId, e.name experimentName, ",
            "e.description experimentDes, ei.image_type imageType, e.deadline_date dueDate, ",
            "e.publish_date publishDate",
            "from module m left join experiment e on e.module_id = m.id",
            "inner join experiment_image ei on e.image_id = ei.id",
            "where course_id=#{courseId, jdbcType=INTEGER}",
            "order by moduleId"
    })
    List<Map> selectModuleExperimentInfoByCourseId(int courseId);


}