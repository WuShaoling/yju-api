package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseResourceMapper courseResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private ClassService classService;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherMapper teacherMapper;

    public Course getCourseById(int courseID) throws ApplicationErrorException {

        Course course = courseMapper.selectByPrimaryKey(courseID);

        if(course == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        course.setImageUrl(this.getImageUrl(courseID));
        return course;
    }

    @Override
    public ResCourseModuleExperiments getCourseModuleExperiments(int classID) throws ApplicationErrorException {
        ResCourseModuleExperiments courseDetail = new ResCourseModuleExperiments();

        Clazz clazz = classService.getClassById(classID);
        Course course = this.getCourseById(clazz.getCourseId());
        courseDetail.setCourseName(course.getName());

        List<ResCourseModuleExperiments.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        for(Module module : moduleMapper.selectByCourseID(clazz.getCourseId())){
            ResCourseModuleExperiments.ModuleInfo moduleInfo = new ResCourseModuleExperiments.ModuleInfo();
            moduleList.add(moduleInfo);

            moduleInfo.setModuleId(module.getId());
            moduleInfo.setModuleName(module.getName());

            List<ResExperimentInfo> experimentInfoList = new ArrayList<>();
            moduleInfo.setModuleContent(experimentInfoList);
            for(Experiment experiment : experimentMapper.selectByModuleId(module.getId())){
                ResExperimentInfo resExperimentInfo = new ResExperimentInfo(experiment);
                experimentInfoList.add(resExperimentInfo);
            }
        }

        return courseDetail;
    }

    @Override
    public ResCourseExperiments getCourseExperiments(int courseId) throws ApplicationErrorException{
        ResCourseExperiments courseDetail = new ResCourseExperiments();

        Course course = this.getCourseById(courseId);
        courseDetail.setCourseId(course.getId());
        courseDetail.setCourseName(course.getName());

        List<ResExperimentInfo> experimentInfoList = new ArrayList<>();
        for(Experiment experiment : experimentMapper.selectByCourseId(courseId)){
            ResExperimentInfo resExperimentInfo = new ResExperimentInfo(experiment);
            experimentInfoList.add(resExperimentInfo);
        }

        return courseDetail;
    }

    @Override
    public ResCourseHomeworks getCourseHomeworks(int classID) throws ApplicationErrorException {
        ResCourseHomeworks courseDetail = new ResCourseHomeworks();

        Clazz clazz = classService.getClassById(classID);
        Course courseInfo = this.getCourseById(clazz.getCourseId());
        courseDetail.setCourseName(courseInfo.getName());

        List<ResCourseHomeworks.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        for(Module module : moduleMapper.selectByCourseID(clazz.getCourseId())){
            ResCourseHomeworks.ModuleInfo moduleInfo = new ResCourseHomeworks.ModuleInfo();
            moduleList.add(moduleInfo);

            moduleInfo.setModuleId(module.getId());
            moduleInfo.setModuleName(module.getName());

            List<ResCourseHomeworks.HomeworkInfo> homeworks = new ArrayList<>();
            moduleInfo.setModuleContent(homeworks);
            for(Homework homework : homeworkMapper.selectByModuleId(module.getId())){
                ResCourseHomeworks.HomeworkInfo homeworkInfo = new ResCourseHomeworks.HomeworkInfo(homework);
                homeworks.add(homeworkInfo);
            }
        }

        return courseDetail;
    }

    @Override
    public ResCourseList getAllCourses() throws ApplicationErrorException {
        ResCourseList courseList = new ResCourseList();
        List<ResCourseList.CourseInfo> courseInfoList = new ArrayList<>();
        courseList.setCourseInfoList(courseInfoList);

        for(Course course : courseMapper.getAllCourses()){
            ResCourseList.CourseInfo courseInfo = new ResCourseList.CourseInfo();
            courseInfoList.add(courseInfo);

            courseInfo.setId(course.getId());
            courseInfo.setCourseName(course.getName());
            courseInfo.setCourseDes(course.getDescription());

            Teacher teacher = teacherService.getTeacherById(course.getTeacherId());
            courseInfo.setTeacherName(teacher.getName());
            courseInfo.setTeacherContact(teacher.getEmail());
        }

        return courseList;
    }

    @Override
    public void createCourse(Course course) throws ApplicationErrorException {
        validateCourse(course);

        courseMapper.insert(course);
    }

    @Override
    public void updateCourse(Course course) throws ApplicationErrorException {
        if(courseMapper.selectByPrimaryKey(course.getId()) == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        validateCourse(course);

        courseMapper.updateByPrimaryKey(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseMapper.deleteByPrimaryKey(courseId);
    }

    private void validateCourse(Course course) throws ApplicationErrorException {
        Teacher teacher = teacherMapper.selectByPrimaryKey(course.getTeacherId());
        if(teacher == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }
    }

    private String getImageUrl(int courseID){
        CourseResource courseResource =
                courseResourceMapper.selectByPrimaryKeyAndType(courseID, ResourceTypeEnum.IMAGE.getCode());

        if(courseResource == null){
            return "";
        }

        return resourceMapper.selectByPrimaryKey(courseResource.getResourceId()).getUrl();
    }
}
