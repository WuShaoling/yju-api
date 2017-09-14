package com.guanshan.phoenix.webapp.shared.util.JPush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.utils.StringUtils;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.message.MessageBody;
import cn.jmessage.api.common.model.message.MessagePayload;
import cn.jmessage.api.message.MessageListResult;
import cn.jmessage.api.message.MessageResult;
import cn.jmessage.api.message.MessageType;
import cn.jmessage.api.message.SendMessageResult;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bennettqian on 02/08/2017.
 */
public class JMessageIM {
    private static Logger LOG = Logger.getLogger(JMessageIM.class);
    private JMessageClient client;

    public JMessageIM(JMessageClient client) {
        this.client = client;
    }

    /**
     * Send single text message by admin, this method will invoke sendMessage() in JMessageClient eventually, whose
     * parameters are as list:
     */
    public void sendSingleTextByAdmin(String to,String from,String content) {

        try {
            MessageBody body = MessageBody.text(content);
            SendMessageResult result = client.sendSingleTextByAdmin(to, from, body);
            LOG.info(String.valueOf(result.getMsg_id()));
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * Send group text message by admin
     */
    public void sendGroupTextByAdmin(String to,String from,String content) {

        try {
            MessageBody body = MessageBody.text(content);
            SendMessageResult result = client.sendGroupTextByAdmin(to, from, body);
            LOG.info(String.valueOf(result.getMsg_id()));
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void sendImageMessage() {
        MessageBody messageBody = new MessageBody.Builder()
                .setMediaId("qiniu/image/r/A92D550D57464CDF5ADC0D79FBD46210")
                .setMediaCrc32(4258069839L)
                .setWidth(43)
                .setHeight(44)
                .setFormat("png")
                .setFsize(2670)
                .build();

        MessagePayload payload = MessagePayload.newBuilder()
                .setVersion(1)
                .setTargetType("single")
                .setTargetId("test_user1")
                .setFromType("admin")
                .setFromId("junit_admin")
                .setMessageType(MessageType.IMAGE)
                .setMessageBody(messageBody)
                .build();

        try {
            SendMessageResult res = client.sendMessage(payload);
            System.out.println(res.getMsg_id());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }

    }

    public void getMessageList() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        try {
            MessageListResult result = client.getMessageList(100000, "2017-07-27 10:10:10", time);
            String cursor = result.getCursor();
            if (null != cursor && StringUtils.isNotEmpty(cursor)) {
                MessageResult[] messages = result.getMessages();
                MessageListResult secondResult = client.getMessageListByCursor(cursor);
                MessageResult[] secondMessages = secondResult.getMessages();
                for(int i = 0;i<messages.length;i++){
                    LOG.info("message history:"+messages[i]);
                }
//                for(int i = 0;i<messages.length;i++){
//                    LOG.info("second message history:"+secondMessages[i]);
//                }

            }

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }


}
