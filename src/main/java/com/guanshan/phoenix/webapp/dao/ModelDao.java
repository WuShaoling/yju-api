package com.guanshan.phoenix.webapp.dao;

import com.guanshan.phoenix.webapp.shared.entity.Model;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by bennettqian on 07/09/2017.
 */
@Mapper
public interface ModelDao{
    Model getByName(String name);
}
