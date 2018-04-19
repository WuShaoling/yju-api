package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.EncryptionUtil;
import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ManagerService;
import com.guanshan.phoenix.service.RancherService;
import com.guanshan.phoenix.webdomain.request.ReqResetPassword;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImp implements ManagerService {

    static Logger log = Logger.getLogger(ManagerServiceImp.class.getName());

    @Value("${default.password}")
    private String defaultPassword;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RancherService rancherService;

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

        try {
            rancherService.createUserVolume(user.getId());
        }catch (Exception e){
            log.error(String.format("Creating volume failed. Error message:%s", e.getMessage()));
            log.log(Level.ERROR, e.getMessage(), e);
            throw new ApplicationErrorException(ErrorCode.GeneralError);
        }
        log.info("Creating volume succeeded.");
        return user;
    }
}
