package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqDeleteModule;
import com.guanshan.phoenix.webdomain.response.ResModuleImages;

public interface ModuleService {
    void createModule(Module module) throws ApplicationErrorException;

    void deleteModule(ReqDeleteModule reqDeleteModule) throws ApplicationErrorException;

    ResModuleImages getModuleImageUrls(int moduleId);
}
