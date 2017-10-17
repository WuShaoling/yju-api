package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.dao.mapper.TermMapper;
import com.guanshan.phoenix.service.TermService;
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

}
