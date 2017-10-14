package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Semester;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.SemesterMapper;
import com.guanshan.phoenix.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.enums.TitleEnum;
import com.guanshan.phoenix.service.ManagerService;
import com.guanshan.phoenix.webdomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImp implements ManagerService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public RespTeacherInfo getAllTeacherInfo() {
        RespTeacherInfo respTeacherInfo = new RespTeacherInfo();

        List<RespTeacherInfo.RespTeacher> respTeacherList = new ArrayList<>();
        List<Teacher> teacherList = teacherMapper.selectAll();
        for (Teacher teacher : teacherList) {
            RespTeacherInfo.RespTeacher respTeacher = new RespTeacherInfo().new RespTeacher();
            respTeacher.setId(teacher.getId());
            respTeacher.setTeacherName(teacher.getName());
            respTeacher.setTeacherTitle(TitleEnum.getStringByCode(teacher.getTitle()));
            respTeacher.setGender(teacher.getGender());
            respTeacher.setTeacherContact(teacher.getEmail());
            respTeacherList.add(respTeacher);
        }
        respTeacherInfo.setRespTeacherList(respTeacherList);

        return respTeacherInfo;
    }

    @Override
    public void addTeacherInfo(ReqAddTeacher reqAddTeacher) {
        Teacher teacher = new Teacher();
        teacher.setTno(reqAddTeacher.getTeacherId());
        teacher.setName(reqAddTeacher.getTeacherName());
        teacher.setTitle(TitleEnum.getCodeByString(reqAddTeacher.getTeacherTitle()));
        teacher.setGender(reqAddTeacher.getGender());
        teacher.setEmail(reqAddTeacher.getTeacherContact());
        teacherMapper.insertSelective(teacher);
    }

    @Override
    public void updateTeacherInfo(ReqUpdateTeacher reqUpdateTeacher) {
        int id = teacherMapper.selectPrimaryKeyByTeacherId(reqUpdateTeacher.getTeacherId());

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setTno(reqUpdateTeacher.getTeacherId());
        teacher.setName(reqUpdateTeacher.getTeacherName());
        teacher.setTitle(TitleEnum.getCodeByString(reqUpdateTeacher.getTeacherTitle()));
        teacher.setGender(reqUpdateTeacher.getGender());
        teacher.setEmail(reqUpdateTeacher.getTeacherContact());
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    @Override
    public void resetTeacherPassword(String  teacherId) {
        int userId = teacherMapper.selectUserIdByTeacherId(teacherId);
        User user = new User();
        user.setId(userId);
        // todo new password ??
        user.setPassword("12345678");
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteTeacherInfo(String teacherId) {
        teacherMapper.deleteByTeacherId(teacherId);
    }

    @Override
    public RespSemesterInfo getAllSemesterInfo() {
        RespSemesterInfo respSemesterInfo = new RespSemesterInfo();

        List<RespSemesterInfo.RespSemester> respSemesterList = new ArrayList<>();
        List<Semester> semesters = semesterMapper.selectAll();
        for (Semester semester : semesters) {
            RespSemesterInfo.RespSemester respSemester = new RespSemesterInfo().new RespSemester();
            respSemester.setId(semester.getId());
            respSemester.setSemesterYear(semester.getYear());
            respSemester.setSemester(semester.getSemester());
            respSemesterList.add(respSemester);
        }
        respSemesterInfo.setRespSemesterList(respSemesterList);
        return respSemesterInfo;
    }

    @Override
    public void addSemesterInfo(ReqAddSemester reqAddSemester) {
        Semester semester = new Semester();
        semester.setYear(reqAddSemester.getSemesterYear());
        semester.setSemester(reqAddSemester.getSemester());
        semesterMapper.insertSelective(semester);
    }

    @Override
    public void updateSemesterInfo(ReqUpdateSemester reqUpdateSemester) {
        Semester semester = new Semester();
        semester.setId(reqUpdateSemester.getId());
        semester.setYear(reqUpdateSemester.getSemesterYear());
        semester.setSemester(reqUpdateSemester.getSemester());
        semesterMapper.updateByPrimaryKeySelective(semester);
    }

    @Override
    public void deleteSemesterInfo(int semesterId) {
        semesterMapper.deleteByPrimaryKey(semesterId);
    }
}
