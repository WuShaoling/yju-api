package com.guanshan.phoenix.webapp.middleware;

import com.guanshan.phoenix.webapp.service.UserService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by bennettqian on 28/06/2017.
 */
@Component
@Import(value ={UserService.class})
public class Receiver {

    @Autowired
    private UserService userService;

    private Logger log = Logger.getLogger(Receiver.class);

    @JmsListener(containerFactory = "myFactory", destination = Const.EXAMINE_QUEUE)
    public void examineBanWord(Map jsonObject){


    }

    @JmsListener(containerFactory = "myFactory", destination = Const.EXAMINE_QUEUE_IMG)
    public void examineBanImg(Map jsonObject){


    }

}
