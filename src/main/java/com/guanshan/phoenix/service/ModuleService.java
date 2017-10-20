package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.error.ApplicationErrorException;

public interface ModuleService {
    void createModule(Module module) throws ApplicationErrorException;

    void deleteModule(int moduleId);
}
