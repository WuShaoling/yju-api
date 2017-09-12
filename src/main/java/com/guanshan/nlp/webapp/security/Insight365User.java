package com.guanshan.nlp.webapp.security;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Wuneng.Zhang on 2017/6/1.
 */
@Data
@Document(collection = "ManagerUser")
public class Insight365User implements Serializable {

    private static final long serialVersionUID = 315579025611690466L;

    @Id
    private String id;

    private String username;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {

        id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}