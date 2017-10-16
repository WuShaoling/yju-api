package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.ModuleResource;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ModuleResourceMapper {
    @Delete({
        "delete from module_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into module_resource (id, module_id, ",
        "resource_id)",
        "values (#{id,jdbcType=INTEGER}, #{moduleId,jdbcType=INTEGER}, ",
        "#{resourceId,jdbcType=INTEGER})"
    })
    int insert(ModuleResource record);

    @InsertProvider(type=ModuleResourceSqlProvider.class, method="insertSelective")
    int insertSelective(ModuleResource record);

    @Select({
        "select",
        "id, module_id, resource_id",
        "from module_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="module_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="resource_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    ModuleResource selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ModuleResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ModuleResource record);

    @Update({
        "update module_resource",
        "set module_id = #{moduleId,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ModuleResource record);
}