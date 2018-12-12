package com.pl.dao;
import com.pl.model.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface UserDao {
    /**
     *删除教师信息
     */
    public boolean deleteTeacher(TeacherEntity user);

    /**
     *教师登录
     */
    public TeacherEntity teacherLogin(TeacherEntity teacherEntity);

    /**
     *管理员登录
     */
    public void adminiLogin(AdministratorEntity administratorEntity);

    /**
     *验证是否存在用户
     */
    public boolean verifyUsername(String phone);

    /**
     *验证是否存在用户
     */
    public boolean verifyTeachername(String phone);

    /**
     *验证密码是否正确
     */
    public boolean verifyPassword(String phone, String password);

    /**
     *验证教师密码是否正确
     */
    public boolean  teacherVerifyPassword(String phone, String password);

    /**
     *查询管理员的信息
     */
    public AdministratorEntity findAdminByPhone(String phone);

    /**
     *查询教师的信息
     */
    public TeacherEntity findTeacherByPhone(String phone);

    public Boolean checkLogin();
}
