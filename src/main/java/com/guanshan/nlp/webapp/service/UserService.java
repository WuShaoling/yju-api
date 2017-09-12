package com.guanshan.nlp.webapp.service;

import com.guanshan.nlp.webapp.dao.UserDao;
import com.guanshan.nlp.webapp.shared.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

/**
 * Created by bennettqian on 25/05/2017.
 */
@Service
@EnableMongoRepositories(basePackages="com.guanshan.nlp.webapp.rds")
public class UserService {
    @Autowired
    private UserDao userDao;

    public void saveUser(User user){
       userDao.insertUser(user);
    }

}
