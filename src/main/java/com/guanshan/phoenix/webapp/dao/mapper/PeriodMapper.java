package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Period;

@Mapper
public interface PeriodMapper {

    @Insert("INSERT INTO period (update_time, name, course_id, term_id, craete_time, id) VALUES (#{updateTime}, #{name}, #{courseId}, #{termId}, #{craeteTime}, #{id})")
    int insert(Period period);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE period SET update_time=#{updateTime}, name=#{name}, course_id=#{courseId}, term_id=#{termId}, craete_time=#{craeteTime}, id=#{id} WHERE id=#{id}")
    int update(Period period);

    @Select("SELECT * FROM period WHERE id=#{id}")
    Period findOne(@Param("id") Integer id);

}