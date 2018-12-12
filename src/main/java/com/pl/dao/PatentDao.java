package com.pl.dao;

import com.pl.model.PatentEntity;
import com.pl.model.ProjectEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface PatentDao {
    /**
     * 专利项目列表
     */
    public List<PatentEntity> list(int start, int end,String patentname);

    /**
     * 专利总条数
     */
    int ListCount(String patentname);
    /**
     * 教师项目列表
     */
    public List<PatentEntity> listByTeacher(int start, int end, String id, String name);

    /**
     * 教师项目总条数
     */
    int ListCountByTeacher(String id,String name);

    /**
     * 添加
     */
    void add(String tid, String patentid,String applicationtime, String name, String author, String authorizeder, String noticeday);

    /**
     * 修改审批状态
     */
    void update(String id,Integer status,String patentid,String author,String patentname,String applicationtime,String authorizeder,String noticeday);

    /**
     * 删除
     */
    void Delete(String id);
}
