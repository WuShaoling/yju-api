package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.ModuleService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.request.ReqAddCourse;
import com.guanshan.phoenix.webdomain.request.ReqDeleteClass;
import com.guanshan.phoenix.webdomain.request.ReqDeleteCourse;
import com.guanshan.phoenix.webdomain.request.ReqDeleteModule;
import com.guanshan.phoenix.webdomain.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private ModuleService moduleService;

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

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentHomeworkMapper studentHomeworkMapper;

    @Autowired
    private StudentClassMapper studentClassMapper;

    public Course getCourseById(int courseID) throws ApplicationErrorException {

        Course course = courseMapper.selectByPrimaryKey(courseID);

        if (course == null) {
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        course.setImageUrl(this.getImageUrl(courseID));
        return course;
    }

    @Override
    public ResCourseModuleExperiments getClassModuleExperiments(int classId) throws ApplicationErrorException {
        Clazz clazz = classService.getClassById(classId);
        Course course = this.getCourseById(clazz.getCourseId());
        return getCourseModuleExperiments(course);
    }

    @Override
    public ResCourseModuleExperiments getCourseModuleExperiments(int courseId) throws ApplicationErrorException {
        Course course = this.getCourseById(courseId);

        return getCourseModuleExperiments(course);
    }

    private ResCourseModuleExperiments getCourseModuleExperiments(Course course) {
        ResCourseModuleExperiments courseDetail = new ResCourseModuleExperiments();

        courseDetail.setCourseName(course.getName());
        courseDetail.setCourseId(course.getId());

        List<ResCourseModuleExperiments.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        ResCourseModuleExperiments.ModuleInfo module = new ResCourseModuleExperiments.ModuleInfo();

        for (Map moduleInfo : moduleMapper.selectModuleExperimentInfoByCourseId(course.getId())) {
            int moduleId = (int) moduleInfo.get("moduleId");
            if (module.getModuleId() != moduleId) {
                module = new ResCourseModuleExperiments.ModuleInfo();
                moduleList.add(module);
                module.setModuleContent(new ArrayList<>());
                module.setModuleId(moduleId);
                module.setModuleName((String) moduleInfo.get("moduleName"));
            }

            if (moduleInfo.get("experimentId") != null) {
                ResExperimentInfo resExperimentInfo = new ResExperimentInfo();
                module.getModuleContent().add(resExperimentInfo);
                resExperimentInfo.setId((int) moduleInfo.get("experimentId"));
                resExperimentInfo.setExperimentName((String) moduleInfo.get("experimentName"));
                resExperimentInfo.setExperimentDes((String) moduleInfo.get("experimentDes"));
                CloudwareTypeEnum cloudwareTypeEnum = CloudwareTypeEnum.fromInt(
                        (int) moduleInfo.get("cloudwareType")
                );
                resExperimentInfo.setCloudwareType(cloudwareTypeEnum == null ? "" : cloudwareTypeEnum.toString());
                resExperimentInfo.setDueDate(Utility.formatDate((Date) moduleInfo.get("dueDate")));
                resExperimentInfo.setPublishDate(Utility.formatDate((Date) moduleInfo.get("publishDate")));
            }
        }

        return courseDetail;
    }

    @Override
    public ResCourseHomeworks getCourseHomeworks(int classID, int studentId) throws ApplicationErrorException {
        if (studentMapper.selectByUserId(studentId) == null) {
            throw new ApplicationErrorException(ErrorCode.StudentNotExists);
        }

        ResCourseHomeworks courseDetail = new ResCourseHomeworks();

        Clazz clazz = classService.getClassById(classID);
        Course courseInfo = this.getCourseById(clazz.getCourseId());
        courseDetail.setCourseName(courseInfo.getName());

        List<ResCourseHomeworks.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        ResCourseHomeworks.ModuleInfo moduleInfo = new ResCourseHomeworks.ModuleInfo();

        for (Map moduleHomeworkInfo : homeworkMapper.selectHomeworkDetailByClassAndStudentId(classID, studentId)) {
            int moduleId = (int) moduleHomeworkInfo.get("moduleId");
            if (moduleId != moduleInfo.getModuleId()) {
                moduleInfo = new ResCourseHomeworks.ModuleInfo();
                moduleList.add(moduleInfo);
                moduleInfo.setModuleId(moduleId);
                moduleInfo.setModuleName((String) moduleHomeworkInfo.get("moduleName"));
                moduleInfo.setModuleContent(new ArrayList<>());
            }

            ResCourseHomeworks.HomeworkInfo homeworkInfo = new ResCourseHomeworks.HomeworkInfo();
            moduleInfo.getModuleContent().add(homeworkInfo);
            homeworkInfo.setId((int) moduleHomeworkInfo.get("homeworkId"));
            homeworkInfo.setHomeworkName((String) moduleHomeworkInfo.get("homeworkName"));
            homeworkInfo.setHomeworkDes((String) moduleHomeworkInfo.get("homeworkDes"));
            CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt((int) moduleHomeworkInfo.get("cloudwareType"));
            homeworkInfo.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());
            homeworkInfo.setDueDate(Utility.formatDate((Date) moduleHomeworkInfo.get("dueDate")));
            homeworkInfo.setPublishDate(Utility.formatDate((Date) moduleHomeworkInfo.get("publishDate")));

            boolean isCompleted = moduleHomeworkInfo.get("studentHomeworkId") != null &&
                    moduleHomeworkInfo.get("submissionDate") != null;
            homeworkInfo.setCompleted(isCompleted);
        }

        return courseDetail;
    }

    @Override
    public ResCourseList getAllCourses() throws ApplicationErrorException {
        ResCourseList courseList = new ResCourseList();
        List<ResCourseList.CourseInfo> courseInfoList = new ArrayList<>();
        courseList.setCourseInfoList(courseInfoList);

        for (Map course : courseMapper.getAllCourses()) {
            ResCourseList.CourseInfo courseInfo = new ResCourseList.CourseInfo();
            courseInfoList.add(courseInfo);

            courseInfo.setId((int) course.get("courseId"));
            courseInfo.setCourseName((String) course.get("courseName"));
            courseInfo.setCourseDes((String) course.get("courseDes"));
            courseInfo.setTeacherId((int) course.get("teacherId"));
            courseInfo.setTeacherName((String) course.get("teacherName"));
            courseInfo.setTeacherContact((String) course.get("teacherContact"));
        }

        return courseList;
    }

    @Override
    public Integer createCourse(ReqAddCourse reqAddCourse) throws ApplicationErrorException {
        Course course = new Course(
                reqAddCourse.getTeacherId(), reqAddCourse.getName(), reqAddCourse.getDescription());
        validateCourse(course);

        courseMapper.insert(course);
        Resource resource = new Resource(
                reqAddCourse.getImageName(), reqAddCourse.getImageUrl(), 0, 0);
        resourceMapper.insert(resource);
        CourseResource courseResource = new CourseResource(
                course.getId(), resource.getId(), ResourceTypeEnum.IMAGE.getCode()
        );
        courseResourceMapper.insert(courseResource);

        return course.getId();
    }

    @Override
    public void updateCourse(Course course) throws ApplicationErrorException {
        if (courseMapper.selectByPrimaryKey(course.getId()) == null) {
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        validateCourse(course);

        courseMapper.updateByPrimaryKey(course);

        if (course.getImageUrl() == null || course.getImageUrl().isEmpty()) {
            return;
        }

        CourseResource courseResource = courseResourceMapper.selectByCourseIdAndType(course.getId(), ResourceTypeEnum.IMAGE.getCode());
        if (courseResource == null) {
            Resource resource = new Resource();
            resource.setUrl(course.getImageUrl());
            resourceMapper.insert(resource);
            courseResource = new CourseResource();
            courseResource.setCourseId(course.getId());
            courseResource.setResourceId(resource.getId());
            courseResourceMapper.insert(courseResource);
        } else {
            Resource resource = resourceMapper.selectByPrimaryKey(courseResource.getResourceId());
            resource.setUrl(course.getImageUrl());
            resourceMapper.updateByPrimaryKey(resource);
        }
    }

    @Override
    public void deleteCourse(ReqDeleteCourse reqDeleteCourse) throws ApplicationErrorException {
        int courseId = reqDeleteCourse.getId();

        Course course = courseMapper.selectByPrimaryKey(courseId);
        if (course == null) {
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

//        if (moduleMapper.isCourseUsedByModule(courseId)) {
//            throw new ApplicationErrorException(ErrorCode.CourseIsUsedByModule);
//        }
//
//        if (clazzMapper.isCourseUsedByClass(courseId)) {
//            throw new ApplicationErrorException(ErrorCode.CourseIsUsedByClass);
//        }

        for (Module module : moduleMapper.selectByCourseID(courseId)) {
            moduleService.deleteModule(new ReqDeleteModule(module.getId()));
        }

        for (Clazz clazz : clazzMapper.selectByCourseId(courseId)) {
            classService.deleteClass(new ReqDeleteClass(clazz.getId()));
        }

        CourseResource courseResource =
                courseResourceMapper.selectByCourseIdAndType(courseId, ResourceTypeEnum.IMAGE.getCode());
        if (courseResource != null) {
            courseResourceMapper.deleteByPrimaryKey(courseResource.getId());
        }
        courseMapper.deleteByPrimaryKey(courseId);
        resourceMapper.deleteByPrimaryKey(courseResource.getResourceId());
    }

    @Override
    public ResHotCourseList getHotCourses() {
        ResHotCourseList resHotCourseList = new ResHotCourseList();
        resHotCourseList.setCourseList(courseMapper.getHotCourses());
        return resHotCourseList;
    }

    @Override
    public ResCommonCourseDetail getCommonCourseDetail(int courseId) throws ApplicationErrorException {
        Course course = this.getCourseById(courseId);

        Teacher teacher = teacherMapper.selectByUserId(course.getTeacherId());
        ResTeacherInfo teacherInfo = new ResTeacherInfo(teacher);
        int classNum = clazzMapper.getClassNumByCourseId(courseId);
        int studentNum = studentClassMapper.getStudentNumByCourseId(courseId);

        ResCommonCourseDetail detail = new ResCommonCourseDetail(teacherInfo, classNum, studentNum, course.getDescription());

        return detail;
    }

    @Override
    public int getCount() {
        return courseMapper.getCount();
    }

    private void validateCourse(Course course) throws ApplicationErrorException {
        Teacher teacher = teacherMapper.selectByUserId(course.getTeacherId());
        if (teacher == null) {
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }
    }

    private String getImageUrl(int courseID) {
        CourseResource courseResource =
                courseResourceMapper.selectByCourseIdAndType(courseID, ResourceTypeEnum.IMAGE.getCode());

        if (courseResource == null) {
            return "";
        }

        return resourceMapper.selectByPrimaryKey(courseResource.getResourceId()).getUrl();
    }
}
