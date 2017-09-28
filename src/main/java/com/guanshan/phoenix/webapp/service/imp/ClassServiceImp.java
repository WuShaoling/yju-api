package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.*;
import com.guanshan.phoenix.webapp.dao.mapper.*;
import com.guanshan.phoenix.webapp.service.ClassService;
import com.guanshan.phoenix.webapp.webdomain.WebClass;
import com.guanshan.phoenix.webapp.webdomain.WebClassDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImp implements ClassService {

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<WebClassDetail> getClassDetail(Integer classId) {
        List<WebClassDetail> webClassDetails = new ArrayList<>();

        List<Integer> studentIds = studentClassMapper.findStudentIdsByClassId(classId);
        for (Integer studentId : studentIds) {
            Student student = studentMapper.findOne(studentId);
            webClassDetails.add(new WebClassDetail(studentId, student.getGender(), student.getName()));
        }

        return webClassDetails;
    }

    @Override
    public List<WebClass> getAllClassesInfo() {
        List<WebClass> webClasses = new ArrayList<>();

        List<Clazz> clazzes = classMapper.findAll();
        for (Clazz clazz : clazzes) {
            Course course = courseMapper.findOne(clazz.getCourse_id());
            Term term = termMapper.findOne(clazz.getTerm_id());
            Teacher teacher = teacherMapper.findOne(course.getTeacher_id());

            webClasses.add(new WebClass(clazz.getId(),
                    clazz.getName(),
                    term.getTerm_year()+" 第"+term.getSequence()+"学期",
                    teacher.getName(),
                    teacher.getContact(),
                    clazz.getSize()));
        }

        return webClasses;
    }
}
