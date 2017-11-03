package com.guanshan.phoenix.authentication.auth;

import com.guanshan.phoenix.authentication.authUser.AuthUserInfo;
import com.guanshan.phoenix.error.ApplicationErrorException;

/**
 * Created by Justin on 2017/6/3.
 */

public interface AuthService {

    /**
     * register
     * @param authUserInfoToAdd
     * @return
     */
    AuthUserInfo register(AuthUserInfo authUserInfoToAdd);

    /**
     * login
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * refresh
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);

    void updatePassword(int userId, String oldPassword, String newPassword, String confirmPassword) throws ApplicationErrorException;
}
