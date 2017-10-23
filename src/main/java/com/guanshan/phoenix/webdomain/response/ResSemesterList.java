package com.guanshan.phoenix.webdomain.response;

import com.guanshan.phoenix.dao.entity.Term;

import java.util.List;

public class ResSemesterList {
    private List<Term> semesterList;

    public List<Term> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(List<Term> semesterList) {
        this.semesterList = semesterList;
    }
}
