package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.StudentHomework;

@Mapper
public interface StudentHomeworkMapper {

    @Insert("INSERT INTO student_homework (student_id, score_comments, answer_url, socre, period_id, answer_content, cloudware_id, id) VALUES (#{studentId}, #{scoreComments}, #{answerUrl}, #{socre}, #{periodId}, #{answerContent}, #{cloudwareId}, #{id})")
    int insert(StudentHomework student_homework);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student_homework SET student_id=#{studentId}, score_comments=#{scoreComments}, answer_url=#{answerUrl}, socre=#{socre}, period_id=#{periodId}, answer_content=#{answerContent}, cloudware_id=#{cloudwareId}, id=#{id} WHERE id=#{id}")
    int update(StudentHomework student_homework);

    @Select("SELECT * FROM student_homework WHERE id=#{id}")
    StudentHomework findOne(@Param("id") Integer id);

}