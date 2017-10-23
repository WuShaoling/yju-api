package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqResetPassword;

public interface ManagerService {

    int resetPassword(ReqResetPassword reqResetPassword) throws ApplicationErrorException;

    User createUser(String username, RoleEnum role);
}
