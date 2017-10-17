package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Term;

public interface CourseService {
    Course getCourseById(int courseID);
}
