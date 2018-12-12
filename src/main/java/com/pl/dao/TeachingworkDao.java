package com.pl.dao;

import com.pl.model.CopyrightEntity;
import com.pl.model.TeachingworkEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface TeachingworkDao {

    /**
     * 工作统计列表
     */
    public List<TeachingworkEntity> listByTeacher(int start, int end, String id, String coursename,String starttime,String endtime,String name);

    /**
     * 总条数
     */
    int ListCountByTeacher(String id,String coursename,String starttime,String endtime,String name);

    /**
     * 添加
     */
    void add(String tid, String classhour,String classnumber, String starttime, String endtime, String coursename, String classname);

    /**
     * 列表
     */
    List<TeachingworkEntity> list(int start, int end,String coursename,String starttime,String endtime);

    /**
     * 总条数
     */
    int ListCount(String coursename,String starttime,String endtime);

    /**
     * 修改
     */
    void update(String id,Integer status,String classhour,String classnumber,String starttime,String endtime,String coursename,String classname);

    /**
     * 删除
     */
    void Delete(String id);
}
