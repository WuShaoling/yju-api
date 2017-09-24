package com.guanshan.phoenix.webapp.authentication.security;

/**
 * Created by Justin on 2017/6/3.
 */

public class JwtAuthenticationResponse {

    private String username;
    private String token;

    public JwtAuthenticationResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
