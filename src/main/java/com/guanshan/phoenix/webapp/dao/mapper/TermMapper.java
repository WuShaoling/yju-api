package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Term;

@Mapper
public interface TermMapper {

    @Insert("INSERT INTO term (term_year, id, sequence) VALUES (#{termYear}, #{id}, #{sequence})")
    int insert(Term term);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE term SET term_year=#{termYear}, id=#{id}, sequence=#{sequence} WHERE id=#{id}")
    int update(Term term);

    @Select("SELECT * FROM term WHERE id=#{id}")
    Term findOne(@Param("id") Integer id);

}