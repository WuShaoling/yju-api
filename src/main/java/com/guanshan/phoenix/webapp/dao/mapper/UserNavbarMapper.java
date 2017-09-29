package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.UserNavbar;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

@Mapper
public interface UserNavbarMapper {

    @Insert("INSERT INTO user_navbar (user_id, navbar_id) VALUES (#{user_id}, #{navbar_id})")
    int insert(UserNavbar userNavbar);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE user_navbar SET nav_url=#{nav_url}, navbar_id=#{navbar_id} WHERE id=#{id}")
    int update(UserNavbar userNavbar);

    @Select("SELECT * FROM user_navbar WHERE id=#{id}")
    UserNavbar findOne(@Param("id") Integer id);

    @Select("SELECT * FROM user_navbar")
    List<UserNavbar> findAll();

    @Select("SELECT * FROM navbar WHERE userId=#{userId}")
    List<UserNavbar> findAllByUserId(@Param("userId") int userId);
}
