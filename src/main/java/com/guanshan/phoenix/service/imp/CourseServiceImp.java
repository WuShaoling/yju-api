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
import com.guanshan.phoenix.webdomain.ResCourseModuleExperiments;
import com.guanshan.phoenix.webdomain.ResCourseHomeworks;
import com.guanshan.phoenix.webdomain.ResCourseList;
import com.guanshan.phoenix.webdomain.ResExperimentInfo;
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

    public Course getCourseById(int courseID) throws ApplicationErrorException {

        Course course = courseMapper.selectByPrimaryKey(courseID);

        if(course == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        course.setImageUrl(this.getImageUrl(courseID));
        return course;
    }

    @Override
    public ResCourseModuleExperiments getCourseExperiments(int classID) throws ApplicationErrorException {
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

            List<ResExperimentInfo> experiments = new ArrayList<>();
            moduleInfo.setModuleContent(experiments);
            for(Experiment experiment : experimentMapper.selectByModuleId(module.getId())){
                ResExperimentInfo resExperimentInfo = new ResExperimentInfo(experiment);
                experiments.add(resExperimentInfo);
            }
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
                ResCourseHomeworks.HomeworkInfo homeworkInfo = new ResCourseHomeworks.HomeworkInfo();
                homeworks.add(homeworkInfo);

                homeworkInfo.setId(homework.getId());
                homeworkInfo.setHomeworkName(homework.getName());
                homeworkInfo.setHomeworkDes(homework.getDescription());
                homeworkInfo.setCloudwareType(
                        CloudwareTypeEnum.fromInt(homework.getCloudwareType()).toString());
                homeworkInfo.setDueDate(homework.getDeadlineDate().toString());
                homeworkInfo.setPublishDate(homework.getPublishDate().toString());
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
        int rowUpdate = courseMapper.updateByPrimaryKey(course);

        if(rowUpdate == 0){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        courseMapper.deleteByPrimaryKey(courseId);
    }

    private void validateCourse(Course course) throws ApplicationErrorException {
        teacherService.getAllTeacherClassInfoById(course.getTeacherId());
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
