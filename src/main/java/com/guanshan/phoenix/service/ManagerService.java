package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;

public interface ManagerService {

    int resetPassword(int userId) throws ApplicationErrorException;

    User createUser(String username, RoleEnum role);
}
