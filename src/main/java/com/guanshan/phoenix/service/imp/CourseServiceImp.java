package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.request.ReqAddCourse;
import com.guanshan.phoenix.webdomain.request.ReqDeleteCourse;
import com.guanshan.phoenix.webdomain.response.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
    private ClazzMapper clazzMapper;

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
    public ResCourseModuleExperiments getClassModuleExperiments(int classId) throws ApplicationErrorException{
        Clazz clazz = classService.getClassById(classId);
        Course course = this.getCourseById(clazz.getCourseId());
        return getCourseModuleExperiments(course.getId());
    }

    @Override
    public ResCourseModuleExperiments getCourseModuleExperiments(int courseId) throws ApplicationErrorException {
        ResCourseModuleExperiments courseDetail = new ResCourseModuleExperiments();

        Course course = this.getCourseById(courseId);
        courseDetail.setCourseName(course.getName());
        courseDetail.setCourseId(course.getId());

        List<ResCourseModuleExperiments.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        for(Module module : moduleMapper.selectByCourseID(courseId)){
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
    public ResCourseHomeworks getCourseHomeworks(int classID) throws ApplicationErrorException {
        if(clazzMapper.selectByPrimaryKey(classID) == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        ResCourseHomeworks courseDetail = new ResCourseHomeworks();

        Clazz clazz = classService.getClassById(classID);
        Course courseInfo = this.getCourseById(clazz.getCourseId());
        courseDetail.setCourseName(courseInfo.getName());

        List<ResCourseHomeworks.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        for(Module module : moduleMapper.selectByCourseID(clazz.getCourseId())){
            ResCourseHomeworks.ModuleInfo moduleInfo = new ResCourseHomeworks.ModuleInfo();

            moduleInfo.setModuleId(module.getId());
            moduleInfo.setModuleName(module.getName());

            List<ResCourseHomeworks.HomeworkInfo> homeworks = new ArrayList<>();
            moduleInfo.setModuleContent(homeworks);
            for(Homework homework : homeworkMapper.selectByModuleIdAndClassId(module.getId(), classID)){
                ResCourseHomeworks.HomeworkInfo homeworkInfo = new ResCourseHomeworks.HomeworkInfo(homework);
                homeworks.add(homeworkInfo);
            }

            if(homeworks.size() > 0){
                moduleList.add(moduleInfo);
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

            Teacher teacher = teacherService.getTeacherByUserId(course.getTeacherId());
            courseInfo.setTeacherName(teacher.getName());
            courseInfo.setTeacherContact(teacher.getEmail());
        }

        return courseList;
    }

    @Override
    public void createCourse(ReqAddCourse reqAddCourse) throws ApplicationErrorException {
        Course course = new Course(
                reqAddCourse.getTeacherId(), reqAddCourse.getName(), reqAddCourse.getDescription());
        validateCourse(course);

        courseMapper.insert(course);
        Resource resource = new Resource(
                reqAddCourse.getImageName(), reqAddCourse.getImageUrl(), "", "");
        resourceMapper.insert(resource);
        CourseResource courseResource = new CourseResource(
            course.getId(), resource.getId(), ResourceTypeEnum.IMAGE.getCode()
        );
        courseResourceMapper.insert(courseResource);
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
    public void deleteCourse(ReqDeleteCourse reqDeleteCourse) throws ApplicationErrorException {
        int courseId = reqDeleteCourse.getId();

        Course course = courseMapper.selectByPrimaryKey(courseId);
        if (course == null) {
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        if (moduleMapper.isCourseUsedByModule(courseId)) {
            throw new ApplicationErrorException(ErrorCode.CourseIsUsedByModule);
        }

        if (clazzMapper.isCourseUsedByClass(courseId)) {
            throw new ApplicationErrorException(ErrorCode.CourseIsUsedByClass);
        }

        CourseResource courseResource =
                courseResourceMapper.selectByCourseIdAndType(courseId, ResourceTypeEnum.IMAGE.getCode());
        if (courseResource != null) {
            courseResourceMapper.deleteByPrimaryKey(courseResource.getId());
        }
        courseMapper.deleteByPrimaryKey(courseId);
        resourceMapper.deleteByPrimaryKey(courseResource.getResourceId());
    }

    private void validateCourse(Course course) throws ApplicationErrorException {
        Teacher teacher = teacherMapper.selectByUserId(course.getTeacherId());
        if(teacher == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }
    }

    private String getImageUrl(int courseID){
        CourseResource courseResource =
                courseResourceMapper.selectByCourseIdAndType(courseID, ResourceTypeEnum.IMAGE.getCode());

        if(courseResource == null){
            return "";
        }

        return resourceMapper.selectByPrimaryKey(courseResource.getResourceId()).getUrl();
    }
}
