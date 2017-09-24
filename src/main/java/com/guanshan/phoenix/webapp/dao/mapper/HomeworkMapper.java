package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.HomeWork;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface HomeworkMapper {

    @Insert("INSERT INTO homework (student_id, experiment_id, date), " +
            "VALUES (#{student_id}, #{experiment_id}, #{date}")
    int insert(HomeWork homeWork);

    @Delete("DELETE FROM homework WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE homework SET period_id=#{period_id}, name=#{name}, description=#{description}, " +
            "cloudware_url=#{cloudware_url} WHERE id=#{id}")
    int update(HomeWork homeWork);

    @Select("SELECT * FROM homework WHERE id=#{id}")
    HomeWork findOne(@Param("id") Integer id);

    @Select("SELECT * FROM homework")
    List<HomeWork> findAll();
}
