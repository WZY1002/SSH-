package com.pl.service.impl;

import com.pl.dao.TeacherDao;
import com.pl.dao.UserDao;
import com.pl.model.TeacherEntity;
import com.pl.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Resource
    private UserDao userDao;

    @Resource
    private TeacherDao teacherDao;

    public List<TeacherEntity> list(int start,int end,String name) {
        List<TeacherEntity> list=teacherDao.list(start,end,name);
        if(list!=null){
            int index=0;
            for (TeacherEntity teacherBO:list) {
                index=index+1;
                teacherBO.setIndex(index);
            }
        }
        return list;
    }

    public int listCount(String name) {
        return teacherDao.ListCount(name);
    }

    public void delete(String id) {
        teacherDao.Delete(id);
    }
}
