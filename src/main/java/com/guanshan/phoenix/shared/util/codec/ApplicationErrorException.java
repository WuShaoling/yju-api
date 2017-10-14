package com.guanshan.phoenix.shared.util.codec;

public class ApplicationErrorException extends Throwable{
    private final ErrorCode errorCode;
    private final Object[] params;

    public ApplicationErrorException(ErrorCode code, Object... params){
        this.errorCode = code;
        this.params = params;
    }

    int getErrorCode(){
        return errorCode.getCode();
    }

    @Override
    public String getMessage() {
        String messageFormat = errorCode.getErrorStringFormat();
        return String.format(messageFormat, params);
    }
}
