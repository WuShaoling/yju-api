package com.guanshan.phoenix.Util;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utility {

    private Utility(){}

    private static Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    public static void ValidateEmail(String email) throws ApplicationErrorException {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new ApplicationErrorException(ErrorCode.InvalidEmail, email);
        }
    }
}
