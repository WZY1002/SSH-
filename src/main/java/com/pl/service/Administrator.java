package com.pl.service;

import com.pl.model.AdministratorEntity;

public interface Administrator {
    /**
     * 管理员登陆
     * @param phone
     * @param password
     */
    public String adminLogin(String phone,String password);

    /**
     * 教师登录
     * @param phone
     * @param password
     */
    public String teacherLogin(String phone,String password);

    /**
     * 添加管理员
     * @param al
     */
    public void add(AdministratorEntity al);
}
