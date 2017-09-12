package com.guanshan.nlp.webapp.shared.util.codec;

/**
 * Created by bennettqian on 16/06/2017.
 */

public class Const {
    public static final int TOPIC_FINISH= 1;  // 待审核
    public static final int TOPIC_IN_PROGRESS= 0;  // 草稿状态
    public static final int TOPIC_ACTIVE = 2;  // 审核通过
    public static final int TOPIC_REJECTED = 3; // 审核拒绝

    public static final int FRIEND_IN_PROGRESS= 0;  // 请求中
    public static final int FRIEND_ACCEPT = 1;  // 接受
    public static final int FRIEND_REJECTED = 2; // 拒绝
    public static final String SUCCESS = "100";
    public static final String FAIL = "400";
    public static final String NOTFOUND = "300";
    public static final String EXIST = "200";
    public static final String Banned = "500";
    public static final int LOG_ACTIVE = 1;
    public static final int LOG_INACTIVE = 0;
    public static final int TOPIC = 0;
    public static final int DIARY = 1;
    public static final int REPLY = 2;
    public static final int SINGLE_CHAT = 0;
    public static final int GROUP_CHAT = 1;
    public static final String DIARY_QUEUE_ADD = "add_diary";
    public static final String DIARY_QUEUE_DELETE = "delete_diary";
    public static final String REPLY_COUNT_QUEUE = "reply_count";
    public static final String BROWSE_COUNT_QUEUE = "browse_count";
    public static final String GROUP_QUEUE_ADD = "add_group";
    public static final String GROUP_QUEUE_MODIFY = "modify_group";
    public static final String GROUP_QUEUE_REMOVE = "remove_group";
    public static final String LABEL_QUEUE_MODIFY = "modify_label";
    public static final String REPLY_QUEUE_REMOVE = "remove_reply";
    public static final String EXAMINE_QUEUE = "examine";
    public static final String EXAMINE_QUEUE_IMG = "examine_img";

    public static final String DEFAULT_IMG_URL = "http://picture.insight365.ai/1499306793393_85aa7bcb940e3db3413484646066149b.png";
    public static final String DEFAULT_FORBID_IMG = "http://picture.insight365.ai/1500453886917_forbidden.jpg";
    public static final String DEFAULT_TOPIC_THUMBNAIL = "http://olemmkx2c.bkt.clouddn.com/1489390368337_headphones.jpg";
    public static final String DEFAULT_GROUPCHAT_IMG = "https://image.flaticon.com/icons/svg/181/181548.svg";
    public static final String JIGUANG_APPKEY = "c500a143748f53a3c32f48d3";
    public static final String JIGUANG_SECRET = "66aeebebf89962c59d232637";
    public static final String NETEASE_SECRETEID = "3783a45dc03c7789205e51a149a956aa";
    public final static String NETEASE_SECRETKEY = "34f13c8becda59112d6ad65f5ce163c7";
    public final static String NETEASE_BUSINESSID_TEXT = "2221d621b553754eafadfb3616c12a66";
    public final static String NETEASE_BUSINESSID_IMAGE = "152430c7b35ac27626ff428176304fbf";

    public static final String NETEASE_TEXT_URL = "https://api.aq.163.com/v3/text/check";
    public static final String NETEASE_IMG_URL = "https://api.aq.163.com/v3/image/check";

    public static final String NOUSER = "";
    public static int BROWSE_THRESHOLD = 1;

    public static final int CHANGE_PWD_TEMPLATE = 137060;  // 短信模板
    public static final int REGEISTER_TEMPLATE = 137053;

    public final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

}
