package com.guanshan.opera.webapp.rds;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * Created by bennettqian on 31/08/2017.
 */
public interface BaseDao<T> {
    public int insert(String sql, Map params);
    public int update(String sql, Map params);
    public T findOne(String sql, Map params);
    public List<T> findAll(String sql, Map params);
    public void delete(String sql, Map params);
}
