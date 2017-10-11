package com.guanshan.phoenix.authentication.authUser;

/**
 * Created by Justin on 2017/6/2.
 */


public interface AuthUserInfoService {

    AuthUserInfo getUserInfo(String username);
    AuthUserInfo addUserInfo(AuthUserInfo authUserInfoToAdd);
}
