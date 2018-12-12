package com.pl.dao;

import com.pl.model.ProjectEntity;
import com.pl.model.ThesisEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface ProjectDao {
    /**
     * 获取项目列表
     */
    public List<ProjectEntity> list(int start, int end,String name);

    /**
     * 项目总条数
     */
    int ListCount(String name);

    /**
     * 教师项目列表
     */
    public List<ProjectEntity> listByTeacher(int start, int end, String id, String name);

    /**
     * 教师项目总条数
     */
    int ListCountByTeacher(String id,String name);

    /**
     * 添加项目
     */
    void add(String tid,String type,String name,String agelimit,String money,String level,String authors);

    /**
     * 修改审批状态
     */
    void update(String id,Integer status,String type,String projectname,String agelimit,String money,String level,String authors);

    /**
     * 删除
     */
    void Delete(String id);
}
