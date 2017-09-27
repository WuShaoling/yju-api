package com.guanshan.phoenix.webapp.dao.mapper;

import org.apache.ibatis.annotations.*;
import com.guanshan.phoenix.webapp.dao.entity.StudentClass;

@Mapper
public interface StudentClassMapper {

    @Insert("INSERT INTO student_class (class_id, student_id, id) VALUES (#{classId}, #{studentId}, #{id})")
    int insert(StudentClass student_class);

    @Delete("DELETE FROM %s WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE student_class SET class_id=#{classId}, student_id=#{studentId}, id=#{id} WHERE id=#{id}")
    int update(StudentClass student_class);

    @Select("SELECT * FROM student_class WHERE id=#{id}")
    StudentClass findOne(@Param("id") Integer id);

}