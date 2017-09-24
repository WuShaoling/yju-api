package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.dao.entity.Instructor;

/**
 * Created by Administrator on 2017/9/22.
 */
public interface InstructorService {

    Instructor getInstructorInfo(String username);
}
