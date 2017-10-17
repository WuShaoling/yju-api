package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImp implements ClassService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public Clazz getClassById(int classID) {
        return clazzMapper.selectByPrimaryKey(classID);
    }

}
