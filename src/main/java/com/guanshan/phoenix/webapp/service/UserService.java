package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.dao.UserDao;
import com.guanshan.phoenix.webapp.shared.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

/**
 * Created by bennettqian on 25/05/2017.
 */
@Service
@EnableMongoRepositories(basePackages="com.guanshan.opera.webapp.rds")
public class UserService {
    @Autowired
    private UserDao userDao;

    public void saveUser(User user){
       userDao.insertUser(user);
    }

}
