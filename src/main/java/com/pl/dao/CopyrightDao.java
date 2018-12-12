package com.pl.dao;

import com.pl.model.CopyrightEntity;
import com.pl.model.MonographsEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface CopyrightDao {
    /**
     * 教师列表
     */
    public List<CopyrightEntity> listByTeacher(int start, int end, String id, String workname);

    /**
     * 教师专著总条数
     */
    int ListCountByTeacher(String id,String workname);

    /**
     * 添加专著
     */
    void add(String tid, String workname,String author, String worktype, String copyrighter, String completetime, String publishtime, String registration, String registid, String access);

    /**
     * 列表
     */
    public List<CopyrightEntity> list(int start, int end,String workname);

    /**
     * 总条数
     */
    int ListCount(String workname);

    /**
     * 修改审批状态
     */
    void update(String id,Integer status,String workname,String author,String worktype,String copyrighter,String completetime,String publishtime,String registration,String registid,String access);

    /**
     * 删除
     */
    void Delete(String id);
}
