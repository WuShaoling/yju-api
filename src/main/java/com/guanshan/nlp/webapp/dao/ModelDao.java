package com.guanshan.nlp.webapp.dao;

import com.guanshan.nlp.webapp.shared.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by bennettqian on 07/09/2017.
 */
@Mapper
public interface ModelDao{
    Model getByName(String name);
}
