package com.guanshan.phoenix.webapp.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Created by bennettqian on 28/06/2017.
 */
@Component
public class MessageSender {

    private Logger log = Logger.getLogger(MessageSender.class);
    @Autowired
    private JmsTemplate jmsTemplate;
    ObjectMapper mapper = new ObjectMapper();

    public void examineTopic(String topicId){
        Map map = new HashMap();
        map.put("topicId", topicId);
        map.put("type", Const.TOPIC);
        log.info("检查帖子敏感词："+topicId);
        sendMessage(map,Const.EXAMINE_QUEUE);
        sendMessage(map,Const.EXAMINE_QUEUE_IMG);
    }




    /**
     * 发送到消息队列
     *
     * @param messgae
     * @param destination
     *
     */
    public void sendMessage(final Map messgae, String destination) {
        try {

//            String destination = this.Queue;
//            if (type == 1) {
//                destination = GoldQueue;
//            }
//            jmsTemplate.getConnectionFactory().createConnection().createSession(false,Session.AUTO_ACKNOWLEDGE).createTopic("test");
            jmsTemplate.convertAndSend(destination, messgae);
        } catch (Exception e) {

        }
    }

}
