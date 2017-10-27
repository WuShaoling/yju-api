package com.guanshan.phoenix.authentication.security;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomHTTP403Filter implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Utility.writeError(request, response, HttpStatus.OK, ErrorCode.NeedAuthentication);
    }
}
