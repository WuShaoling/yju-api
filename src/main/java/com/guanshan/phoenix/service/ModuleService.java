package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResModuleImages;

public interface ModuleService {
    void createModule(Module module) throws ApplicationErrorException;

    void deleteModule(int moduleId) throws ApplicationErrorException;

    ResModuleImages getModuleImageUrls(int moduleId);
}
