package com.guanshan.phoenix.error;

public enum ErrorCode {
    @ErrorMessage("操作成功")
    Success(0),

    @ErrorMessage("旧密码不正确。")
    IncorrectOldPassword(1),

    @ErrorMessage("该课程不存在")
    CourseNotExists(2),

    @ErrorMessage("该班级不存在")
    ClassNotExists(3),

    @ErrorMessage("该作业不存在")
    HomeworkNotExists(4),

    @ErrorMessage("该学生不存在")
    StudentNotExists(5),

    @ErrorMessage("该教师不存在")
    TeacherNotExists(6),

    @ErrorMessage("该学生作业不存在")
    StudentHomeworkNotExists(7),

    @ErrorMessage("该学期'%s'已存在")
    TermAlreadyExists(8),

    @ErrorMessage("该学期不存在")
    TermNotExists(9),

    @ErrorMessage("属性'头衔'不合法")
    InvalidTitle(10),

    @ErrorMessage("电子邮件'%s'格式不正确")
    InvalidEmail(11),

    @ErrorMessage("该教师'%s'已存在")
    TeacherAlreadyExists(12),

    @ErrorMessage("该学期'%d'不合法")
    InvalidSemester(13),

    @ErrorMessage("该学期正在被班级表使用")
    TermIsUsedByClass(14),

    @ErrorMessage("该教师正在被课程表使用")
    TeacherIsUsedByCourse(15),

    @ErrorMessage("该班级正在被作业表使用")
    ClassIsUsedByHomework(16),

    @ErrorMessage("该班级正在被学生班级表使用")
    ClassIsUsedByStudentClass(17),

    @ErrorMessage("该课程正在被课时表使用")
    CourseIsUsedByModule(18),

    @ErrorMessage("该课程正在被班级表使用")
    CourseIsUsedByClass(19),

    @ErrorMessage("该学生不在这个班级里")
    StudentNotInClass(20),

    @ErrorMessage("该课时不存在")
    ModuleNotExists(21),

    @ErrorMessage("该作业中课时与班级属于不同的课程，无法添加")
    HomeworkModuleClassBelongsToDifferentCourse(22),

    @ErrorMessage("云件类型非法")
    InvalidCloudwareType(23),

    @ErrorMessage("该作业正在被学生作业表使用")
    HomeworkUsedByStudentHomework(24),

    @ErrorMessage("该课时正在被实验表使用")
    ModuleUsedByExperiment(25),

    @ErrorMessage("该课时正在被作业表使用")
    ModuleUsedByHomework(26),

    @ErrorMessage("该学生已在该班级")
    StudentAlreadyInClass(27),

    @ErrorMessage("该学生'%S'已存在")
    StudentAlreadyExists(28),

    @ErrorMessage("学号为'%s'的学生名字叫'%s'。是否要覆盖？")
    DuplicateStudentNoFound(29),

    @ErrorMessage("该实验不存在")
    ExperimentNotFound(30),

    @ErrorMessage("该实验正在被学生实验表使用")
    ExperimentUsedByStudentExperiment(31),

    @ErrorMessage("该用户已存在")
    UserAlreadyExists(32),

    @ErrorMessage("该实体已存在")
    EntityAlreadyExists(33),

    @ErrorMessage("该学生实验已存在")
    StudentExperimentExists(34),

    @ErrorMessage("该学生实验不存在")
    StudentExperimentNotFound(35),

    @ErrorMessage("该学生作业已存在")
    StudentHomeworkExists(36),

    @ErrorMessage("该文件类型错误")
    FileTypeError(37),

    @ErrorMessage("该文件不为图片")
    FileIsNotImage(38),

    @ErrorMessage("该文件不为Markdown")
    FileIsNotMarkdown(39),

    @ErrorMessage("服务器出错，请联系管理员")
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
