package com.pl.service;

import com.pl.model.TeacherEntity;

import java.util.List;

public interface TeacherService {

    /***
     * 获取教师列表
     */
    public List<TeacherEntity> list(int pageSize,int pageNum,String name);

    int listCount(String name);

    void delete(String id);
}
