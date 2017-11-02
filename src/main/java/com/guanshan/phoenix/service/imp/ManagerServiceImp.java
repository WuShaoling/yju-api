package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.EncryptionUtil;
import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.cloudwareDomain.ReqCreateVolume;
import com.guanshan.phoenix.cloudwareDomain.ResCloudware;
import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ManagerService;
import com.guanshan.phoenix.webdomain.request.ReqResetPassword;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ManagerServiceImp implements ManagerService {

    static Logger log = Logger.getLogger(ManagerServiceImp.class.getName());

    @Value("${default.password}")
    private String defaultPassword;

    @Value("${cloudware.createVolumeUrl}")
    private String createVolumeUrl;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int resetPassword(ReqResetPassword reqResetPassword) throws ApplicationErrorException {
        User user = new User();
        user.setId(reqResetPassword.getUserId());

        user.setPassword(EncryptionUtil.encryptPassword(defaultPassword));
        userMapper.updateByPrimaryKeySelective(user);

        return 0;
    }

    @Override
    public User createUser(String username, RoleEnum role) throws ApplicationErrorException {
        User user = new User();

        user.setUsername(username);
        user.setRole(role.getCode());
        user.setPassword(EncryptionUtil.encryptPassword(defaultPassword));

        userMapper.insert(user);

        log.info(String.format("Start to create volume for user %d...", user.getId()));

        ReqCreateVolume reqCreateVolume = new ReqCreateVolume();
        reqCreateVolume.setUserId(user.getId());
        reqCreateVolume.setSecret("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmb28iOiJiYXIiLCJpYXQiOjE1MDU4MTM0NTd9.Ftw1yHeUrqdNvymFZcIpuEoS0RHBFZqu4MfUZON9Zm0");

        try {
            ResCloudware resCloudware = restTemplate.postForObject(createVolumeUrl, reqCreateVolume, ResCloudware.class);
            if (resCloudware.getErrorCode() != 0) {
                log.error(String.format("Creating volume failed. Error code returned %d.", resCloudware.getErrorCode()));
                throw new ApplicationErrorException(ErrorCode.GeneralError);
            }
        }catch (RestClientException e){
            log.error(String.format("Creating volume failed. Error message:%s", e.getMessage()));
            Utility.logError(log, e);
            throw new ApplicationErrorException(ErrorCode.GeneralError);
        }
        log.info("Creating volume succeeded.");
        return user;
    }
}
