package com.pl.service.impl;

import com.pl.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.pl.model.AdministratorEntity;
import com.pl.service.Administrator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.print.attribute.standard.RequestingUserName;

@Service
public class AdministratorImpl implements Administrator {
    @Autowired
    private SessionFactory sessionFactory;

    @Resource
    private UserDao userDao;


    public void add(AdministratorEntity al) {
            userDao.adminiLogin(al);
    }

    /**
     * 管理员登录
     */
    public String adminLogin(String phone, String password) {
        try {
            if(userDao.verifyUsername(phone)){
                if(userDao.verifyPassword(phone,password)){
                    return "true";
                }
                return "密码错误";
            }
            return "用户不存在";
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    /**
     * 教师登录
     */
    public String teacherLogin(String phone, String password) {
        try {
            if(userDao.verifyTeachername(phone)){
                if(userDao.teacherVerifyPassword(phone,password)){
                    return "true";
                }
                return "密码错误";
            }
            return "教师不存在";
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
