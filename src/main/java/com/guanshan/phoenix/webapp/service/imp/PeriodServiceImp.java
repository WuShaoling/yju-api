package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Period;
import com.guanshan.phoenix.webapp.dao.mapper.PeriodMapper;
import com.guanshan.phoenix.webapp.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Service
public class PeriodServiceImp implements PeriodService {
    @Autowired
    private PeriodMapper periodMapper;

    @Override
    public List<Period> getAllPeriods(Integer courseId) {
        return periodMapper.findAllByCourseId(courseId);
    }
}
