package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.webdomain.ResCourseExperiments;
import com.guanshan.phoenix.webdomain.ResCourseHomeworks;
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
    private ClazzMapper clazzMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    public Course getCourseById(int courseID) throws ApplicationErrorException {

        Course course = courseMapper.selectByPrimaryKey(courseID);

        if(course == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        course.setImageUrl(this.getImageUrl(courseID));
        return course;
    }

    @Override
    public ResCourseExperiments getCourseExperiments(int courseID) throws ApplicationErrorException {
        ResCourseExperiments courseDetail = new ResCourseExperiments();

        Course courseInfo = this.getCourseById(courseID);
        courseDetail.setCourseName(courseInfo.getName());

        List<ResCourseExperiments.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        for(Module module : moduleMapper.selectByCourseID(courseID)){
            ResCourseExperiments.ModuleInfo moduleInfo = new ResCourseExperiments.ModuleInfo();
            moduleList.add(moduleInfo);

            moduleInfo.setModuleId(module.getId());
            moduleInfo.setModuleName(module.getName());

            List<ResCourseExperiments.ExperimentInfo> experiments = new ArrayList<>();
            moduleInfo.setModuleContent(experiments);
            for(Experiment experiment : experimentMapper.selectByModuleId(module.getId())){
                ResCourseExperiments.ExperimentInfo experimentInfo = new ResCourseExperiments.ExperimentInfo();
                experiments.add(experimentInfo);

                experimentInfo.setId(experiment.getId());
                experimentInfo.setExperimentName(experiment.getName());
                experimentInfo.setExperimentDes(experiment.getDescription());
                experimentInfo.setCloudwareType(
                        CloudwareTypeEnum.fromInt(experiment.getCloudwareType()).toString());
            }
        }

        return courseDetail;
    }

    @Override
    public ResCourseHomeworks getCourseHomeworks(int classID) throws ApplicationErrorException {
        ResCourseHomeworks courseDetail = new ResCourseHomeworks();

        Clazz clazzInfo = clazzMapper.selectByPrimaryKey(classID);
        if(clazzInfo == null){
            throw new ApplicationErrorException(ErrorCode.ClassIDNotExists);
        }
        Course courseInfo = this.getCourseById(clazzInfo.getCourseId());
        courseDetail.setCourseName(courseInfo.getName());

        List<ResCourseHomeworks.ModuleInfo> moduleList = new ArrayList<>();
        courseDetail.setModuleList(moduleList);
        for(Module module : moduleMapper.selectByCourseID(clazzInfo.getCourseId())){
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

    private String getImageUrl(int courseID){
        CourseResource courseResource =
                courseResourceMapper.selectByPrimaryKeyAndType(courseID, ResourceTypeEnum.IMAGE.getCode());

        if(courseResource == null){
            return "";
        }

        return resourceMapper.selectByPrimaryKey(courseResource.getResourceId()).getUrl();
    }
}
