package com.guanshan.phoenix.webapp.dao.mapper;

import com.guanshan.phoenix.webapp.dao.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Mapper
public interface TaskMapper {

    @Insert("INSERT INTO task (student_id, experiment_id, percent, date) " +
            "VALUES (#{studentId}, #{experimentId}, #{percent}, #{date})")
    int insert(Task task);

    @Delete("DELETE FROM task WHERE id={id}")
    int deltet(@Param("id") Integer id);

    @Update("UPDATE task SET percent=#{percent}, date=#{date} WHERE student_id=#{studentId} AND experiment_id={experimentId}")
    int update(Task task);

    @Select("SELECT * FROM student WHERE id=#{id}")
    Task findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student")
    List<Task> findAll();
}
