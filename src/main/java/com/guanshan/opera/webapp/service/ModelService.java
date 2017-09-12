package com.guanshan.opera.webapp.service;

import com.guanshan.opera.webapp.dao.ModelDao;
import com.guanshan.opera.webapp.shared.entity.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * Created by e on 2017/8/30.
 */
@Service
@EnableMongoRepositories(basePackages = "com.guanshan.opera.webapp.rds")
public class ModelService {

    @Autowired
    private ModelDao dao;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Model findOne(String name){
        return dao.getByName(name);
    }
}
