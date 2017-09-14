package com.guanshan.phoenix.webapp.security;

import com.guanshan.phoenix.webapp.shared.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by bennettqian on 24/05/2017.
 */
@Component
public interface Insight365UserRepository extends MongoRepository<User,String>{
    public Insight365User findByPhone(String username);
}
