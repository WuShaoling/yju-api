package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.StudentClass;
import org.springframework.security.access.method.P;

import java.util.List;

@Mapper
public interface StudentClassMapper {

    @Insert("INSERT INTO student_class (class_id, student_id, id) VALUES (#{class_id}, #{student_id}, #{id})")
    int insert(StudentClass student_class);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student_class SET class_id=#{class_id}, student_id=#{student_id}, id=#{id} WHERE id=#{id}")
    int update(StudentClass student_class);

    @Select("SELECT * FROM student_class WHERE id=#{id}")
    StudentClass findOne(@Param("id") Integer id);

    @Select("SELECT * FROM student_class")
    List<StudentClass> findAll();

    @Select("SELECT student_id FROM student_class WHERE class_id=#{class_id}")
    List<Integer> findStudentIdsByClassId(@Param("class_id") Integer classId);

}