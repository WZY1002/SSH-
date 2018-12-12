package com.pl.dao.impl;

import com.pl.dao.UserDao;
import com.pl.model.AdministratorEntity;
import com.pl.model.TeacherEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource(name="hibernateTemplate")
    private HibernateTemplate template;

    public boolean deleteTeacher(TeacherEntity user) {
        return false;
    }

    public TeacherEntity teacherLogin(TeacherEntity teacherEntity) {
        return null;
    }


    public void adminiLogin(AdministratorEntity administratorEntity) {
        template.save(administratorEntity);
    }

    // 验证教师密码是否正确
    public boolean teacherVerifyPassword(String phone, String password) {
        List queryList = template.find("select password from TeacherEntity where mobile_phone=?", phone);
        return ( queryList.get(0).toString() ).equals(password);
    }

    // 验证用户名是否存在
    public boolean verifyUsername(String phone) {
        List<AdministratorEntity> users = (List<AdministratorEntity>) template.find("from AdministratorEntity where phone=?", phone);
        return users.isEmpty() ? false:true;
    }


    // 验证教师名是否存在
    public boolean verifyTeachername(String phone) {
            List<TeacherEntity> teacherEntities = (List<TeacherEntity>) template.find("from TeacherEntity where mobile_phone=?", phone);
        return teacherEntities.isEmpty() ? false:true;
    }

    // 验证密码是否正确
    public boolean verifyPassword(String phone, String password) {
        List queryList = template.find("select password from AdministratorEntity where phone=?", phone);
        return ( queryList.get(0).toString() ).equals(password);
    }

    public AdministratorEntity findAdminByPhone(String phone) {
        List<AdministratorEntity> users =
                (List<AdministratorEntity>) template.find("from AdministratorEntity where phone=?", phone);
        return ( users.isEmpty() ? null:users.get(0));
    }

    public TeacherEntity findTeacherByPhone(String phone) {
        List<TeacherEntity> users = (List<TeacherEntity>) template.find("from TeacherEntity where mobile_phone=?", phone);
        return ( users.isEmpty() ? null:users.get(0));
    }

    /**
     *登录状体检测
     */
    public Boolean checkLogin(){
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String phone=(String)session.getAttribute("phone");
        if(StringUtils.isEmpty(phone)){
            return false;
        }
        return true;
    }
}
