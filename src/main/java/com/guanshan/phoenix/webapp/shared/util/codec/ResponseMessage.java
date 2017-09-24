package com.guanshan.phoenix.webapp.shared.util.codec;

/**
 * Created by Dell on 2017/6/12.
 */
public class ResponseMessage<T> {
    String errorCode;

    String message;

    T data;

    public ResponseMessage() {
    }

    public ResponseMessage(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResponseMessage(String errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
