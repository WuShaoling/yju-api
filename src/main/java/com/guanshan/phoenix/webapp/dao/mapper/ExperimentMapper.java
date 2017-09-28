package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Experiment;

import java.util.List;

@Mapper
public interface ExperimentMapper {

    @Insert("INSERT INTO experiment (update_time, create_time, period_id, description, cloudware_id, id, name) VALUES (#{update_time}, #{create_time}, #{period_id}, #{description}, #{cloudware_id}, #{id}, #{name})")
    int insert(Experiment experiment);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE experiment SET update_time=#{update_time}, create_time=#{create_time}, period_id=#{period_id}, description=#{description}, cloudware_id=#{cloudware_id}, id=#{id}, name=#{name} WHERE id=#{id}")
    int update(Experiment experiment);

    @Select("SELECT * FROM experiment WHERE id=#{id}")
    Experiment findOne(@Param("id") Integer id);

    @Select("SELECT * FROM experiment")
    List<Experiment> findAll();

    @Select("SELECT * FROM experiment WHERE period_id=#{period_id}")
    List<Experiment> findByPeriodId(@Param("period_id") Integer periodId);
}