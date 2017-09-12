package com.guanshan.nlp.webapp.shared.util.JPush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.NoDisturbPayload;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.friend.FriendNote;
import cn.jmessage.api.user.UserInfoResult;
import cn.jmessage.api.user.UserListResult;
import cn.jmessage.api.user.UserStateResult;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bennettqian on 26/07/2017.
 */
public class JMessageUser {
    private static Logger LOG = Logger.getLogger(JMessageUser.class);
    private JMessageClient client;

    public JMessageUser(JMessageClient client) {
        this.client = client;
    }
    public boolean registerUsers(String username, String password) {

        try {

            List<RegisterInfo> users = new ArrayList<RegisterInfo>();

            RegisterInfo user = RegisterInfo.newBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .build();


            users.add(user);

            RegisterInfo[] regUsers = new RegisterInfo[users.size()];

            String res = client.registerUsers(users.toArray(regUsers));
            LOG.info(res);
            return true;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            return  false;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            return false;
        }
    }

    public void getUserInfo() {


        try {
            UserInfoResult res = client.getUserInfo("test_user");
            LOG.info(res.getUsername());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void getUserState() {

        try {
            UserStateResult result = client.getUserState("test_user");
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public boolean updatePassword(String phone,String passowrod) throws APIConnectionException, APIRequestException {

        try {
            client.updateUserPassword(phone, passowrod);
            return true;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            throw e;
//            return false;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            throw e;
//            return false;
        }
    }

    public boolean updateUserInfo(String username, String nickname, String birthday, String signature, int gender,
                               String region, String address) {

        try {
            client.updateUserInfo(username, nickname, birthday, signature, gender, region, address);
            return true;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            return false;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            return false;
        }
    }

    public void getUsers() {

        try {
            UserListResult res = client.getUserList(0, 30);
            LOG.info(res.getOriginalContent());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public boolean deleteUser(String phone) {

        try {
            client.deleteUser(phone);
            return true;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            return false;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get admins by appkey
     */
    public void getAdminListByAppkey() {
        try {
            UserListResult res = client.getAdminListByAppkey(0, 1);
            LOG.info(res.getOriginalContent());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void getBlackList() {
        try {
            UserInfoResult[] result = client.getBlackList("username");
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void removeBlacklist() {
        try {
            ResponseWrapper response = client.removeBlacklist("test_user", "test_user1", "test_user2");
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void addBlackList() {
        try {
            ResponseWrapper response = client.addBlackList("username", "user1", "user2");
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void setNoDisturb(long test_gid) {
        try {
            NoDisturbPayload payload = new NoDisturbPayload.Builder()
                    .setAddSingleUsers("test_user1", "test_user2")
                    .setAddGroupIds(test_gid)
                    .build();
            ResponseWrapper response = client.setNoDisturb("test_user", payload);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void addFriends(String phone,String toAdd) throws APIRequestException, APIConnectionException {
        try {
            ResponseWrapper response = client.addFriends(phone,toAdd);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            throw e;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            throw e;
        }
    }

    public void deleteFriends(String phone, String toDelete) throws APIConnectionException, APIRequestException {
        try {
            ResponseWrapper response = client.deleteFriends(phone, toDelete);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            throw e;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            throw e;
        }
    }

    public void updateFriendsNote() {
        try {
            List<FriendNote> friendNotes = new ArrayList<FriendNote>();
            FriendNote friendNote1 = new FriendNote.Builder()
                    .setNoteName("note name 1")
                    .setOthers("test")
                    .setUsername("test_user1")
                    .builder();
            FriendNote friendNote2 = new FriendNote.Builder()
                    .setNoteName("note name 2")
                    .setOthers("test")
                    .setUsername("test_user2")
                    .builder();
            friendNotes.add(friendNote1);
            friendNotes.add(friendNote2);
            FriendNote[] array = new FriendNote[friendNotes.size()];
            ResponseWrapper result = client.updateFriendsNote("test_user", friendNotes.toArray(array));
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public UserInfoResult[] getFriends(String username) throws APIConnectionException, APIRequestException {
        try {
            UserInfoResult[] userInfoArray = client.getFriendsInfo(username);
            return userInfoArray;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            throw e;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            throw e;
        }
    }
}
