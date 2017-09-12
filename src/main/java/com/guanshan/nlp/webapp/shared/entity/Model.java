package com.guanshan.nlp.webapp.shared.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by e on 2017/8/30.
 */
@Data
@Document(collection = "ModelTest")
public class Model {
    @Id
    private int id;

    private String name;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String time) {
        this.date = time;
    }

    public Model(){

    }
    public Model(String name,String time) {
        this.name = name;
        this.date = time;
    }
}
