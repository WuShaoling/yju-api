package com.guanshan.phoenix.authentication.security;

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
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(String.format("{\"errorCode\": %d,\n" +
                "\"message\": \"%s\"}", ErrorCode.NeedAuthentication.getCode(), ErrorCode.NeedAuthentication.getErrorStringFormat()));
    }
}
