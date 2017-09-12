package com.guanshan.opera.webapp.shared.util.JPush;

import cn.jmessage.api.JMessageClient;
import com.guanshan.opera.webapp.shared.util.codec.Const;
import org.apache.log4j.Logger;

/**
 * Created by bennettqian on 18/07/2017.
 */
public class JMessageHelper {
    private static Logger LOG = Logger.getLogger(JPushHelper.class);
    private JMessageClient client;
    private JMessageGroup jMessageGroup;
    private JMessageUser jMessageUser;
    private JMessageIM jMessageIM;
    private static JMessageHelper jMessageHelper;

    private JMessageHelper(JMessageClient client){
        this.client = client;
        this.jMessageGroup = new JMessageGroup(client);
        this.jMessageUser = new JMessageUser(client);
        this.jMessageIM = new JMessageIM(client);
    }

    public static JMessageHelper getInstance(){
        if(jMessageHelper == null){
            jMessageHelper = new JMessageHelper(new JMessageClient(Const.JIGUANG_APPKEY,Const.JIGUANG_SECRET));
        }
        return jMessageHelper;
    }

    public JMessageGroup group(){
        return this.jMessageGroup;
    }

    public JMessageUser user(){
        return this.jMessageUser;
    }

    public JMessageIM im(){
        return this.jMessageIM;
    }

}
