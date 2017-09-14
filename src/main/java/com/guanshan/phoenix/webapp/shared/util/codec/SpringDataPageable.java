package com.guanshan.phoenix.webapp.shared.util.codec;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by bennettqian on 11/07/2017.
 */
public class SpringDataPageable implements Serializable, Pageable {
    // 当前页
    private Integer pagenumber;
    // 当前页面条数
    private Integer pagesize;
    //排序条件
    private Sort sort;
    private static SpringDataPageable springDataPageable;

    private SpringDataPageable(){
        this.pagenumber = 1;
        this.pagesize = 10;
    }

    public static SpringDataPageable getInstance(){
        if(springDataPageable == null){
            return new SpringDataPageable();
        }
        return springDataPageable;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
    @Override
    public int getPageNumber() {
        return getPagenumber();
    }

    @Override
    public int getPageSize() {
        return getPagesize();
    }

    @Override
    public int getOffset() {
        return (getPagenumber() - 1) * getPagesize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
    public Integer getPagenumber() {
        return pagenumber;
    }
    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }
    public Integer getPagesize() {
        return pagesize;
    }
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}
