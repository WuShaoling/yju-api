package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Experiment;

@Mapper
public interface ExperimentMapper {

    @Insert("INSERT INTO experiment (update_time, period_id, description, cloudware_id, craete_time, id, name) VALUES (#{updateTime}, #{periodId}, #{description}, #{cloudwareId}, #{craeteTime}, #{id}, #{name})")
    int insert(Experiment experiment);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE experiment SET update_time=#{updateTime}, period_id=#{periodId}, description=#{description}, cloudware_id=#{cloudwareId}, craete_time=#{craeteTime}, id=#{id}, name=#{name} WHERE id=#{id}")
    int update(Experiment experiment);

    @Select("SELECT * FROM experiment WHERE id=#{id}")
    Experiment findOne(@Param("id") Integer id);

}