package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Navbar;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

@Mapper
public interface NavbarMapper {

    @Insert("INSERT INTO navbar (nav_url) VALUES (#{nav_url})")
    int insert(Navbar navbar);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE navbar SET nav_url=#{nav_url} WHERE id=#{id}")
    int update(Navbar navbar);

    @Select("SELECT * FROM navbar WHERE id=#{id}")
    Navbar findOne(@Param("id") Integer id);

    @Select("SELECT * FROM navbar")
    List<Navbar> findAll();

}
