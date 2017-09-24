package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Instructor;
import com.guanshan.phoenix.webapp.dao.mapper.InstructorMapper;
import com.guanshan.phoenix.webapp.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/22.
 */

@Service
public class InstructorServiceImp implements InstructorService {

    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    public Instructor getInstructorInfo(String username) {
        return instructorMapper.findOneUsername(username);
    }
}
