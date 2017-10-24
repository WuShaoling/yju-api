package com.guanshan.phoenix.error;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    ResponseMessage handleDulicateKeyException(HttpServletRequest request, Throwable ex) {
        //todo: log original exception
        DuplicateKeyException duplicateException = (DuplicateKeyException)ex;
        String errorMessage = duplicateException.getMessage();

        if(errorMessage.contains("username_UNIQUE")){
            return new ResponseMessage.Fail(new ApplicationErrorException(ErrorCode.UserAlreadyExists));
        }

        return new ResponseMessage.Fail(new ApplicationErrorException(ErrorCode.EntityAlreadyExists));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseMessage handleThrowableException(HttpServletRequest request, Throwable ex) {
        //todo: log original exception
        return new ResponseMessage.Fail(new ApplicationErrorException(ErrorCode.GeneralError));
    }
}