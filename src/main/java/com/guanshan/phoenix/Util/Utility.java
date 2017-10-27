package com.guanshan.phoenix.Util;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utility {

    private Utility(){}

    private static Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static String shortDateFormat = "yyyy-MM-dd";

    public static void validateEmail(String email) throws ApplicationErrorException {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new ApplicationErrorException(ErrorCode.InvalidEmail, email);
        }
    }

    public static String formatDate(Date date){
        if(date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.parse(date);
    }

    public static Date parseShortDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(shortDateFormat);
        return sdf.parse(date);
    }

    public static void writeError(HttpServletRequest request, HttpServletResponse response,
                                  HttpStatus status, ErrorCode errorCode) throws IOException {
        response.setStatus(status.value());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(String.format("{\"errorCode\": %d,\n" +
                "\"message\": \"%s\"}", errorCode.getCode(), errorCode.getErrorStringFormat()));
    }
}
