package com.pl.service;

import com.pl.model.TeacherEntity;
import com.pl.model.ThesisEntity;

import java.util.List;

public interface ThesisService {
    /***
     * 获取教师列表
     */
    public List<ThesisEntity> list(int start,int end, String title);

    int listCount(String title);

    void delete(String id);
}
