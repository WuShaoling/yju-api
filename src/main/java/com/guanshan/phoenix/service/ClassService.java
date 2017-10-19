package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResClassDetail;

public interface ClassService {
    Clazz getClassById(int classID) throws ApplicationErrorException;

    ResClassDetail getClassDetailInfo(int classID) throws ApplicationErrorException;
}
