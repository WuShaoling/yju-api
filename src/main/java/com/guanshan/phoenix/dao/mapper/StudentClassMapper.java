package com.guanshan.phoenix.dao.mapper;

import com.guanshan.phoenix.dao.entity.StudentClass;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface StudentClassMapper {
    @Delete({
        "delete from student_class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into student_class (id, student_id, ",
        "class_id)",
        "values (#{id,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, ",
        "#{classId,jdbcType=INTEGER})"
    })
    int insert(StudentClass record);

    @InsertProvider(type=StudentClassSqlProvider.class, method="insertSelective")
    int insertSelective(StudentClass record);

    @Select({
        "select",
        "id, student_id, class_id",
        "from student_class",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    StudentClass selectByPrimaryKey(Integer id);

    @UpdateProvider(type=StudentClassSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentClass record);

    @Update({
        "update student_class",
        "set student_id = #{studentId,jdbcType=INTEGER},",
          "class_id = #{classId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StudentClass record);

    @Select({
            "select *",
            "from student_class",
            "where student_id = #{studentId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="student_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="class_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<StudentClass> selectByStudentID(Integer studentId);
}