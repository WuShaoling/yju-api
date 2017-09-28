package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.*;
import com.guanshan.phoenix.webapp.dao.mapper.*;
import com.guanshan.phoenix.webapp.service.CourseService;
import com.guanshan.phoenix.webapp.webdomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private PeriodMapper periodMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public List<WebCourse> getAllCourseInfo() {
        List<WebCourse> webCourses = new ArrayList<>();

        List<Course> courses = courseMapper.findAll();
        for (Course course : courses) {
            Teacher teacher = teacherMapper.findOne(course.getTeacher_id());
            Term term = termMapper.findOne(course.getTerm_id());

            webCourses.add(new WebCourse(course.getId(),
                    course.getName(),
                    course.getDescription(),
                    term.getTerm_year()+" 第"+term.getSequence()+"学期",
                    teacher.getName(),
                    teacher.getContact()));
        }

        return webCourses;
    }

    @Override
    public List<WebPeriodDetail> getAllPeriodDetailInfo(int courseId) {
        List<WebPeriodDetail> webPeriodDetails = new ArrayList<>();

        List<Period> periods = periodMapper.findByCourseId(courseId);
        for (Period period : periods) {
            WebPeriodDetail webPeriodDetail = new WebPeriodDetail();
            webPeriodDetail.setPeriodId(period.getId());
            webPeriodDetail.setModuleName(period.getName());

            List<Experiment> experiments = experimentMapper.findByPeriodId(period.getId());
            List<WebExperiment> webExperiments = new ArrayList<>();
            for (Experiment experiment : experiments) {
                webExperiments.add(new WebExperiment(experiment.getId(),
                        experiment.getName(),
                        experiment.getDescription(),
                        experiment.getCloudware_id()));
            }
            webPeriodDetail.setModuleExperiment(webExperiments);

            webPeriodDetails.add(webPeriodDetail);
        }

        return webPeriodDetails;
    }

    @Override
    public List<WebTerm> getALlTermInfo() {
        List<WebTerm> webTerms = new ArrayList<>();

        List<Term> terms = termMapper.findAll();
        for (Term term : terms) {
            webTerms.add(new WebTerm(term.getId(),
                    term.getTerm_year(),
                    term.getSequence()));
        }
        return webTerms;
    }

    @Override
    public WebStudentCourse getStudentCourseInfo(int studentId) {
        WebStudentCourse webStudentCourses = new WebStudentCourse();
        List<WebCourse> webCourseList = new ArrayList<>();

        List<StudentCourse> studentCourses = studentCourseMapper.findByStudentId(studentId);
        webStudentCourses.setStudentId(studentId);

        for (StudentCourse studentCourse: studentCourses) {
            Course course = courseMapper.findOne(studentCourse.getCourse_id());
            Term term = termMapper.findOne(studentCourse.getTerm_id());
            Teacher teacher = teacherMapper.findOne(course.getTeacher_id());

            webCourseList.add(new WebCourse(course.getId(),
                    course.getName(),
                    course.getDescription(),
                    term.getTerm_year()+" 第"+term.getSequence()+"学期",
                    teacher.getName(),
                    teacher.getContact()
                    ));
        }
        webStudentCourses.setWebCourseList(webCourseList);

        return webStudentCourses;
    }
}
