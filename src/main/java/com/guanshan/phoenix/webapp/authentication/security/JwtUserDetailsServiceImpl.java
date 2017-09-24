package com.guanshan.phoenix.webapp.authentication.security;

import com.guanshan.phoenix.webapp.authentication.authUser.AuthUserInfo;
import com.guanshan.phoenix.webapp.authentication.authUser.AuthUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Justin on 2017/6/2.
 */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserInfoService authUserInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserInfo authUserInfo = authUserInfoService.getUserInfo(username);

        if (authUserInfo == null) {
            throw new UsernameNotFoundException(String.format("No authUserInfo found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(authUserInfo);
        }
    }
}
