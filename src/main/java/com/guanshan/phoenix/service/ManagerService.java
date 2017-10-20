package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;

public interface ManagerService {

    int resetPassword(int userId) throws ApplicationErrorException;
}
