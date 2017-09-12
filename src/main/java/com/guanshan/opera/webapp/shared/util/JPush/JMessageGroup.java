package com.guanshan.opera.webapp.shared.util.JPush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.group.CreateGroupResult;
import cn.jmessage.api.group.GroupInfoResult;
import cn.jmessage.api.group.GroupListResult;
import cn.jmessage.api.group.MemberListResult;
import org.apache.log4j.Logger;

/**
 * Created by bennettqian on 26/07/2017.
 */
public class JMessageGroup {
    private static Logger LOG = Logger.getLogger(JMessageGroup.class);

    private JMessageClient client;

    public JMessageGroup(JMessageClient client) {
        this.client = client;
    }

    public CreateGroupResult createGroup(String user, String groupname, String desc, String... userList) {
        try {
            CreateGroupResult res = client.createGroup(user, groupname, desc, userList);
            LOG.info(res.getName());
            return res;
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            return null;
        }
    }

    public void getGroupInfo(Long gid) {

        try {
            GroupInfoResult res = client.getGroupInfo(gid);
            LOG.info(res.getOriginalContent());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void getGroupMemberList(Long gid) {

        try {
            MemberListResult res = client.getGroupMembers(gid);
            LOG.info(res.getOriginalContent());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * Get group list by appkey, this method will invoke getGroupListByAppkey() in GroupClient, whose parameters
     * are list as follow:
     */
    public void getGroupListByAppkey(int start, int count) {

        try {
            GroupListResult res = client.getGroupListByAppkey(start,count);
            LOG.info(res.getOriginalContent());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public boolean manageGroup(Long gid,String[] addList, String [] removeList) {

        try {
//            String[] addList = {"baobao148"};
//            String[] removeList = {"baobao148"};
            client.addOrRemoveMembers(gid, addList, removeList );
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

    public boolean updateGroupInfo(Long gid,String groupname,String description) {

        try {
            client.updateGroupInfo(gid, groupname, description);
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


    public boolean deleteGroup(Long gid) {

        try {
            client.deleteGroup(gid);
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
}
