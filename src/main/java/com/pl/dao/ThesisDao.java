package com.pl.dao;

import com.pl.model.TeacherEntity;
import com.pl.model.ThesisEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface ThesisDao {

    /**
     * 获取论文列表
     */
    public List<ThesisEntity> list(int start, int end,String title);

    /**
     * 教师论文列表
     */
    public List<ThesisEntity> listByTeacher(int start, int end, String id,String title);

    /**
     * 论文条目总条数
     */
    int ListCount(String title);

    /**
     * 教师论文总条数
     */
    int ListCountByTeacher(String id,String title);

    /**
     * 修改
     */
    void update(String id,Integer status,String title,String published_time,String magazine_name,String authors);

    /**
     * 删除
     */
    void Delete(String id);

    void add(String id,String title,String published_time,String magazine_name,String authors);
}
