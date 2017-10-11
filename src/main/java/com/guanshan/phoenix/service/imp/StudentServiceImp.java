package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.service.StudentService;
import com.guanshan.phoenix.webdomain.ReqPasswdModify;
import com.guanshan.phoenix.webdomain.RespStudentCourse;
import com.guanshan.phoenix.webdomain.RespStudentCourseDetail;
import com.guanshan.phoenix.webdomain.RespStudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private PeriodMapper periodMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public int updatePassword(ReqPasswdModify reqPasswdModify) {
        Student student = studentMapper.selectByPrimaryKey(reqPasswdModify.getId());
        User user = userMapper.selectByPrimaryKey(student.getUserId());

        // todo 加密
        if (user.getPassword().equals(reqPasswdModify.getOldPass())) {
            user.setPassword(reqPasswdModify.getNewPass());
            userMapper.updateByPrimaryKey(user);
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public RespStudentCourse getStudentCourses(int studentId) {
        List<Integer> courseIds = studentCourseMapper.selectCourseIdsByStudentId(studentId);

        List<RespStudentCourse.RespCourse> respCourseList = new ArrayList<>();
        for (Integer courseId : courseIds) {
            Course course = courseMapper.selectByPrimaryKey(courseId);
            Semester semester = semesterMapper.selectByPrimaryKey(course.getSemesterId());
            // todo more than one teacher ??
            Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourseMapper.selectTeacherIdByCourseId(courseId).get(0));

            respCourseList.add(new RespStudentCourse().new RespCourse(courseId,
                    course.getImage(),
                    course.getDuration(),
                    course.getStudentNumber(),
                    course.getDescription(),
                    course.getCourseSid(),
                    course.getName(),
                    semester.getYear()+" "+semester.getSemester(),
                    teacher.getEmail(),
                    teacher.getName()));
        }

        return new RespStudentCourse(respCourseList);
    }

    @Override
    public RespStudentCourseDetail getCourseDetail(int courseId) {
        RespStudentCourseDetail respStudentCourseDetail = new RespStudentCourseDetail();

        List<RespStudentCourseDetail.RespPeriod> respPeriodList = new ArrayList<>();
        List<Period> periodList = periodMapper.selectByCourseId(courseId);
        for (Period period : periodList) {
            RespStudentCourseDetail.RespPeriod respPeriod = new RespStudentCourseDetail().new RespPeriod();
            respPeriod.setModuleName(period.getName());

            List<Experiment> experimentList = experimentMapper.selectByPeriodId(period.getId());
            List<RespStudentCourseDetail.RespExperiment> respExperimentList = new ArrayList<>();
            for (Experiment experiment : experimentList) {
                RespStudentCourseDetail.RespExperiment respExperiment = new RespStudentCourseDetail().new RespExperiment();
                respExperiment.setId(experiment.getId());
                respExperiment.setExperimentName(experiment.getName());
                respExperiment.setExperimentDes(experiment.getDescription());
                respExperiment.setDueDate(experiment.getExpireDate().toString());
                respExperiment.setDate(experiment.getPublishDate().toString());
                respExperimentList.add(respExperiment);
            }
            respPeriod.setModuleContent(respExperimentList);
            respPeriodList.add(respPeriod);
        }
        respStudentCourseDetail.setRespPeriodList(respPeriodList);

        return respStudentCourseDetail;
    }

    @Override
    public RespStudentHomework getStudentHomework(int courseId) {
        RespStudentHomework respStudentHomework = new RespStudentHomework();

        List<RespStudentHomework.RespPeriod> respPeriodList = new ArrayList<>();
        List<Period> periodList = periodMapper.selectByCourseId(courseId);
        for (Period period : periodList) {
            RespStudentHomework.RespPeriod respPeriod = respStudentHomework.new RespPeriod();
            respPeriod.setModuleName(period.getName());

            List<RespStudentHomework.RespHomework> respHomeworkList = new ArrayList<>();
            List<Experiment> experimentList = experimentMapper.selectByPeriodId(period.getId());
            // todo homework info??
            for (Experiment experiment : experimentList) {
                RespStudentHomework.RespHomework respHomework = respStudentHomework.new RespHomework();
                respHomework.setId(experiment.getId());
                respHomework.setExperimentName(experiment.getName());
                respHomework.setExperimentDes(experiment.getDescription());
                respHomework.setDueDate(experiment.getExpireDate().toString());
                respHomework.setDate(experiment.getPublishDate().toString());

                respHomeworkList.add(respHomework);
            }
            respPeriod.setModuleContent(respHomeworkList);
            respPeriodList.add(respPeriod);
        }
        respStudentHomework.setRespPeriodList(respPeriodList);

        return respStudentHomework;
    }
}
