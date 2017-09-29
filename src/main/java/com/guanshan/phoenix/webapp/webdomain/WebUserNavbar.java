package com.guanshan.phoenix.webapp.webdomain;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */
public class WebUserNavbar {

    private int userId;
    private List<String> navbarList;

    public WebUserNavbar() {
    }

    public WebUserNavbar(int userId, List<String> navbarList) {
        this.userId = userId;
        this.navbarList = navbarList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getNavbarList() {
        return navbarList;
    }

    public void setNavbarList(List<String> navbarList) {
        this.navbarList = navbarList;
    }
}
