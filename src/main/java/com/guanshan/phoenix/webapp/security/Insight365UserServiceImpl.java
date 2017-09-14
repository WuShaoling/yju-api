package com.guanshan.phoenix.webapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * @author zhangwuneng
 */
@Service("userDetailsService")
@EnableMongoRepositories(basePackages= "com.guanshan.phoenix.webapp.security")
public class Insight365UserServiceImpl implements UserDetailsService {
	
    private static Logger logger = LoggerFactory.getLogger(Insight365UserServiceImpl.class);

    @Autowired
	Insight365UserRepository insight365UserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Insight365User user = insight365UserRepository.findByPhone(username);
		if (user == null ) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }
		return new Insight365UserDetails(user);
	}

}
