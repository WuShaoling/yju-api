package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.RoleNavlist;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoleNavlistMapper {
    @Delete({
        "delete from role_navlist",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into role_navlist (id, role, ",
        "navlist_id)",
        "values (#{id,jdbcType=INTEGER}, #{role,jdbcType=INTEGER}, ",
        "#{navlistId,jdbcType=INTEGER})"
    })
    int insert(RoleNavlist record);

    @InsertProvider(type=RoleNavlistSqlProvider.class, method="insertSelective")
    int insertSelective(RoleNavlist record);

    @Select({
        "select",
        "id, role, navlist_id",
        "from role_navlist",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="role", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="navlist_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    RoleNavlist selectByPrimaryKey(Integer id);

    @UpdateProvider(type=RoleNavlistSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleNavlist record);

    @Update({
        "update role_navlist",
        "set role = #{role,jdbcType=INTEGER},",
          "navlist_id = #{navlistId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoleNavlist record);
}