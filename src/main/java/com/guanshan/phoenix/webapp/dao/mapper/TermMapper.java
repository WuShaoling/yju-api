package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.Term;

import java.util.List;

@Mapper
public interface TermMapper {

    @Insert("INSERT INTO term (term_year, id, sequence) VALUES (#{term_year}, #{id}, #{sequence})")
    int insert(Term term);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE term SET term_year=#{term_year}, id=#{id}, sequence=#{sequence} WHERE id=#{id}")
    int update(Term term);

    @Select("SELECT * FROM term WHERE id=#{id}")
    Term findOne(@Param("id") Integer id);

    @Select("SELECT * FROM term")
    List<Term> findAll();

}