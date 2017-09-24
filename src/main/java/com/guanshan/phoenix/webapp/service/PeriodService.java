package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.dao.entity.Period;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
public interface PeriodService {

    List<Period> getAllPeriods(Integer courseId);
}
