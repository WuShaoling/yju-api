package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.dao.mapper.TermMapper;
import com.guanshan.phoenix.enums.SemesterEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.TermService;
import com.guanshan.phoenix.webdomain.request.ReqDeleteSemester;
import com.guanshan.phoenix.webdomain.response.ResSemesterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermServiceImp implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private ClazzMapper clazzMapper;

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
        validate(term);

        termMapper.insert(term);
    }

    @Override
    public void update(Term term) throws ApplicationErrorException {
        if(termMapper.selectByPrimaryKey(term.getId()) == null){
            throw new ApplicationErrorException(ErrorCode.TermNotExists);
        }

        validate(term);

        termMapper.updateByPrimaryKey(term);
    }

    @Override
    public void delete(ReqDeleteSemester reqDeleteSemester) throws ApplicationErrorException {
        int termId = reqDeleteSemester.getSemesterId();

        if(termMapper.selectByPrimaryKey(termId) == null){
            throw new ApplicationErrorException(ErrorCode.TermNotExists);
        }

        if(clazzMapper.isTermUsedByClass(termId)){
            throw new ApplicationErrorException(ErrorCode.TermIsUsedByClass);
        }

        termMapper.deleteByPrimaryKey(termId);
    }

    private void validate(Term term) throws ApplicationErrorException {
        if(termMapper.selectByYearAndSemester(term.getYear(), term.getSemester()) != null){
            throw new ApplicationErrorException(ErrorCode.TermAlreadyExists, term.getDescription());
        }

        if(SemesterEnum.fromInt(term.getSemester()) == null){
            throw new ApplicationErrorException(ErrorCode.InvalidSemester, term.getSemester());
        }
    }
}
