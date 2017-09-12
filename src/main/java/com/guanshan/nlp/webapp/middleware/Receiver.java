package com.guanshan.nlp.webapp.middleware;

import cn.jpush.api.push.model.PushPayload;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.guanshan.nlp.webapp.service.UserService;
import com.guanshan.nlp.webapp.shared.entity.User;
import com.guanshan.nlp.webapp.shared.util.codec.HttpClient4Utils;
import com.guanshan.nlp.webapp.shared.util.JPush.JPushHelper;
import com.guanshan.nlp.webapp.shared.util.codec.Const;
import com.guanshan.nlp.webapp.shared.util.codec.HtmlParser;
import com.guanshan.nlp.webapp.shared.util.codec.StrGenerator;
import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
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
