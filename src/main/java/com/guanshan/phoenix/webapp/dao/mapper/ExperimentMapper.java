package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface ExperimentMapper {

    @Insert("INSERT INTO experiment (period_id, name, description, cloudware_url), " +
            "VALUES (#{period_id}, #{name}, #{description}, #{cloudware_url}")
    int insert(Experiment experiment);

    @Delete("DELETE FROM experiment WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE experiment SET period_id=#{period_id}, name=#{name}, description=#{description}, " +
            "cloudware_url=#{cloudware_url} WHERE id=#{id}")
    int update(Experiment experiment);

    @Select("SELECT * FROM experiment WHERE id=#{id}")
    Experiment findOne(@Param("id") Integer id);

    @Select("SELECT * FROM experiment")
    List<Experiment> findAll();

    @Select("SELECT * FROM experiment WHERE period_id=#{periodId}")
    List<Experiment> findAllByPeriodId(@Param("periodId") Integer periodId);

}
