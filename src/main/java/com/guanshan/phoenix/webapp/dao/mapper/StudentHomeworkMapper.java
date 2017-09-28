package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.StudentHomework;

import java.util.List;

@Mapper
public interface StudentHomeworkMapper {

    @Insert("INSERT INTO student_homework (student_id, score_comments, answer_url, score, period_id, answer_content, cloudware_id, id) VALUES (#{student_id}, #{score_comments}, #{answer_url}, #{score}, #{period_id}, #{answer_content}, #{cloudware_id}, #{id})")
    int insert(StudentHomework student_homework);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student_homework SET student_id=#{student_id}, score_comments=#{score_comments}, answer_url=#{answer_url}, score=#{score}, period_id=#{period_id}, answer_content=#{answer_content}, cloudware_id=#{cloudware_id}, id=#{id} WHERE id=#{id}")
    int update(StudentHomework student_homework);

    @Select("SELECT * FROM student_homework WHERE id=#{id}")
    StudentHomework findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student_homework")
    List<StudentHomework> findAll();

}