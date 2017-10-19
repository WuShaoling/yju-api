package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.dao.mapper.TermMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.TermService;
import com.guanshan.phoenix.webdomain.ResSemesterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermServiceImp implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Override
    public Term getTermById(int termID) {
        return termMapper.selectByPrimaryKey(termID);
    }

    @Override
    public ResSemesterList getAllTerms() {
        ResSemesterList semesterList = new ResSemesterList();
        semesterList.setSemesterList(termMapper.getAllTerms());
        return semesterList;
    }

    @Override
    public void create(Term term) throws ApplicationErrorException {
        if(termMapper.selectByYearAndSemester(term.getYear(), term.getSemester()) != null){
            throw new ApplicationErrorException(ErrorCode.TermAlreadyExists, term.getDescription());
        }

        termMapper.insert(term);
    }

    @Override
    public void update(Term term) throws ApplicationErrorException {
        int rowUpdated = termMapper.updateByPrimaryKey(term);

        if(rowUpdated == 0){
            throw new ApplicationErrorException(ErrorCode.TermNotExists);
        }
    }

    @Override
    public void delete(int termId) {
        termMapper.deleteByPrimaryKey(termId);
    }
}
