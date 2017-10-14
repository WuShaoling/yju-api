package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.shared.util.codec.ApplicationErrorException;
import com.guanshan.phoenix.shared.util.codec.ErrorCode;
import com.guanshan.phoenix.shared.util.codec.ResponseMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApplicationErrorException.class)
    @ResponseBody
    ResponseMessage handleApplicationErrorException(HttpServletRequest request, Throwable ex) {
        //todo: log original exception
        return new ResponseMessage.Fail((ApplicationErrorException)ex);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseMessage handleThrowableException(HttpServletRequest request, Throwable ex) {
        //todo: log original exception
        return new ResponseMessage.Fail(new ApplicationErrorException(ErrorCode.GeneralError));
    }
}