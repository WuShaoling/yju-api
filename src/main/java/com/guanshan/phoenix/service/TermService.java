package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResSemesterList;

public interface TermService {
    Term getTermById(int termID);

    ResSemesterList getAllTerms();

    void create(Term term) throws ApplicationErrorException;

    void update(Term term) throws ApplicationErrorException;

    void delete(int termId) throws ApplicationErrorException;
}
