package com.pl.dao;

import com.pl.model.CopyrightEntity;
import com.pl.model.MonographsEntity;
import com.pl.model.PatentEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface MonographsDao {
    /**
     * 教师专著列表
     */
    public List<MonographsEntity> listByTeacher(int start, int end, String id, String name);

    /**
     * 教师专著总条数
     */
    int ListCountByTeacher(String id,String name);

    /**
     * 添加专著
     */
    void add(String tid, String monographsname,String author, String publishtime, String completetime);

    /**
     * 专利项目列表
     */
    public List<MonographsEntity> list(int start, int end, String monographsname);

    /**
     * 专利总条数
     */
    int ListCount(String monographsname);

    /**
     * 修改审批状态
     */
    void update(String id,Integer status,String monographsname,String author,String publishtime,String completetime);

    /**
     * 删除
     */
    void Delete(String id);

}
