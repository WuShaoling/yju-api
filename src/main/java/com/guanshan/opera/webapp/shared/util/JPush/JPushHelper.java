package com.guanshan.opera.webapp.shared.util.JPush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.guanshan.opera.webapp.shared.util.codec.Const;
import org.apache.log4j.Logger;

import static cn.jpush.api.push.model.notification.PlatformNotification.ALERT;


/**
 * Created by bennettqian on 04/07/2017.
 */
public class JPushHelper {
    private  JPushClient jPushClient;
    private static JPushHelper jPushHelper;
    private Logger log = Logger.getLogger(JPushHelper.class);

    private JPushHelper(JPushClient jPushClient){
        this.jPushClient = jPushClient;

    }
    public static JPushHelper getInstance(){
        if(jPushHelper == null){
            jPushHelper = new JPushHelper(new JPushClient(Const.JIGUANG_SECRET,Const.JIGUANG_APPKEY,null, ClientConfig.getInstance()));

        }
        return jPushHelper;

    }

    public void jpush(PushPayload payload){

        try {
            PushResult result = jPushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }

    public static PushPayload buildPushObject_all_alias_alert(String alias,String publisher) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert("您的好友 "+publisher+" 发送了一条日记，快来看看!"))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, "title", null))
                .build();
    }
}
