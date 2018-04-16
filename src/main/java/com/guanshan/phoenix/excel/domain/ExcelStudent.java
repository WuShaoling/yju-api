package com.guanshan.phoenix.excel.domain;

import java.util.List;

/**
 * Created by Administrator on 2017/10/22.
 */
public class ExcelStudent {

    List<ExcelStudentElement> excelStudentElementList;

    public class ExcelStudentElement {
        private String studentNum;
        private String studentName;
        private int gender;

        public String getStudentNum() {
            return studentNum;
        }

        public void setStudentNum(String studentNum) {
            this.studentNum = studentNum;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }

    public List<ExcelStudentElement> getExcelStudentElementList() {
        return excelStudentElementList;
    }

    public void setExcelStudentElementList(List<ExcelStudentElement> excelStudentElementList) {
        this.excelStudentElementList = excelStudentElementList;
    }

}
