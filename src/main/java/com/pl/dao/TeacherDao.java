package com.pl.dao;

import com.pl.model.TeacherEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface TeacherDao {
    /**
     * 获取教师列表
     */
    public  List<TeacherEntity> list(int start,int end,String name);

    /**
     * 教师总条数
     */
    int ListCount(String name);

    void Delete(String id);

    TeacherEntity teacherInfo(String id);

    void update(String id,String name,String password,String nation,String birth,String place,String address,String mobile_phone,String home_phone,String identity,String education,String school,String politics,String healthy,String department,String photo);

    /**
     * 新增
     */
    void add(String id,String name,String password,String nation,String birth,String place,String address,String mobile_phone,String home_phone,String identity,String education,String school,String politics,String healthy);
}
