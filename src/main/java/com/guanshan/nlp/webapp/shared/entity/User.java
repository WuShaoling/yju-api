package com.guanshan.nlp.webapp.shared.entity;

import com.guanshan.nlp.webapp.shared.util.codec.Const;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by bennettqian on 24/05/2017.
 */
@Data
@Document(collection = "User")
public class User{

    @Id
    private int id;
    private String username;
    private String password;
    private String phone;
    private int role;
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleCode() {
        return role;
    }

    public void setRoleCode(int roleCode) {
        this.role = roleCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
