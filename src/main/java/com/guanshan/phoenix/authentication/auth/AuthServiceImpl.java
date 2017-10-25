package com.guanshan.phoenix.authentication.auth;

import com.guanshan.phoenix.authentication.authUser.AuthUserInfo;
import com.guanshan.phoenix.authentication.authUser.AuthUserInfoService;
import com.guanshan.phoenix.authentication.security.JwtTokenUtil;
import com.guanshan.phoenix.authentication.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

/**
 * Created by Justin on 2017/6/3.
 */

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private AuthUserInfoService authUserInfoService;

    @Value("${token.tokenHeader}")
    private String tokenHead;

    @Value("${token.initRole}")
    private String initRole;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            AuthUserInfoService authUserInfoService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authUserInfoService = authUserInfoService;
    }

    @Override
    public AuthUserInfo register(AuthUserInfo authUserInfoToAdd) {
        final String username = authUserInfoToAdd.getUsername();
        if (authUserInfoService.getUserInfo(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = authUserInfoToAdd.getPassword();
        authUserInfoToAdd.setPassword(encoder.encode(rawPassword));
        authUserInfoToAdd.setLastPasswordResetDate(new Date());
        authUserInfoToAdd.setRoles(Collections.singletonList(initRole));
        return authUserInfoService.addUserInfo(authUserInfoToAdd);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
