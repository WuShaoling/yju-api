package com.guanshan.phoenix.error;

public enum ErrorCode {
    @ErrorMessage("操作成功")
    Success(0),

    @ErrorMessage("旧密码不正确。")
    IncorrectOldPassword(1),

    @ErrorMessage("该课程不存在")
    CourseNotExists(2),

    @ErrorMessage("该班级不存在")
    ClassIDNotExists(3),

    @ErrorMessage("该作业不存在")
    HomeworkIDNotExists(4),

    @ErrorMessage("该学生不存在")
    StudentIDNotExists(5),

    @ErrorMessage("服务器出错，请联系管理员。")
    GeneralError(Integer.MAX_VALUE);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getErrorStringFormat(){
        String format = "";
        try{
            format = ErrorCode.class.getDeclaredField(this.toString()).getAnnotation(ErrorMessage.class).value();
        }catch (NoSuchFieldException ex){
            //Impossible to occur
        }
        return format;
    }
}
