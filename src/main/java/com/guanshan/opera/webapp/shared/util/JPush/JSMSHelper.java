package com.guanshan.opera.webapp.shared.util.JPush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import com.guanshan.opera.webapp.shared.util.codec.Const;
import com.guanshan.opera.webapp.shared.util.codec.ResponseMessage;
import org.apache.log4j.Logger;

/**
 * Created by bennettqian on 11/07/2017.
 */
public class JSMSHelper {
    private SMSClient smsClient;
    private static JSMSHelper jsmsHelper;
    private static Logger log = Logger.getLogger(JPushHelper.class);

    private JSMSHelper(SMSClient smsClient){
        this.smsClient = smsClient;
    }

    public static JSMSHelper getInstance(){
        if(jsmsHelper == null){
            jsmsHelper = new JSMSHelper(new SMSClient(Const.JIGUANG_SECRET,Const.JIGUANG_APPKEY));
        }
        return jsmsHelper;
    }

    public String sendSMSCode(String phone, int template, ResponseMessage responseMessage) {
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobildNumber(phone)
                .setTempId(template)
                .build();
        String result = null;
        try {
            SendSMSResult res = smsClient.sendSMSCode(payload);
            System.out.println(res.toString());
            log.info(res.toString());
            result = res.getMessageId();
            responseMessage.setData(result);
            responseMessage.setErrorCode(Const.SUCCESS);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            responseMessage.setMessage("Connection error. Should retry later. ");
            responseMessage.setErrorCode(Const.FAIL);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
            responseMessage.setMessage("Error response from JPush server. Should review and fix it. ");
            responseMessage.setErrorCode(Const.FAIL);
        }
        return result;
    }


    public Boolean sendValidSMSCode(String msg_id,String code) {
        try {
            ValidSMSResult res = smsClient.sendValidSMSCode(msg_id, code);
            System.out.println(res.toString());
            log.info(res.toString());
            return res.getIsValid();
        } catch (APIConnectionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.error("Connection error. Should retry later. ", e);
            return false;
        } catch (APIRequestException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 50010) {
                // do something
            }
            System.out.println(e.getStatus() + " errorCode: " + e.getErrorCode() + " " + e.getErrorMessage());
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
            return false;
        }
    }

    /**
     *  The default value of ttl is 60 seconds.
     */
    public void sendVoiceSMSCode() {
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobildNumber("13800138000")
                .setTTL(90)
                .build();
        try {
            SendSMSResult res = smsClient.sendVoiceSMSCode(payload);
            log.info(res.toString());
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        }
    }

    public void sendTemplateSMS() {
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobildNumber("13800138000")
                .setTempId(1)
                .addTempPara("test", "jpush")
                .build();
        try {
            SendSMSResult res = smsClient.sendTemplateSMS(payload);
            log.info(res.toString());
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        }
    }
}
