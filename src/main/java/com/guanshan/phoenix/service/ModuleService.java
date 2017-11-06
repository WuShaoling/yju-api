package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqAddModuleResource;
import com.guanshan.phoenix.webdomain.request.ReqDeleteModule;
import com.guanshan.phoenix.webdomain.request.ReqDeleteModuleResource;
import com.guanshan.phoenix.webdomain.response.ResModuleId;
import com.guanshan.phoenix.webdomain.response.ResModuleImages;

public interface ModuleService {
    ResModuleId createModule(Module module) throws ApplicationErrorException;

    void deleteModule(ReqDeleteModule reqDeleteModule) throws ApplicationErrorException;

    ResModuleImages getModuleImageUrls(int moduleId);

    Integer addModuleResource(ReqAddModuleResource reqAddModuleResource) throws ApplicationErrorException;

    void deleteModuleResource(ReqDeleteModuleResource reqDeleteModuleResource) throws ApplicationErrorException;
}
