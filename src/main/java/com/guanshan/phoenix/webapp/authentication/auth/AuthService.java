package com.guanshan.phoenix.webapp.authentication.auth;

import com.guanshan.phoenix.webapp.authentication.authUser.AuthUserInfo;

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
}
