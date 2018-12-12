package com.pl.action;

import com.pl.dao.UserDao;
import com.pl.dao.impl.UserDaoImpl;
import com.pl.model.AdministratorEntity;
import com.pl.model.TeacherEntity;
import com.pl.service.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Scope("prototype")
public class IndexAction {

    @Resource
    private UserDao userDao;

    private JsonResult jsonResult;

    private AdministratorEntity adminBO;

    private TeacherEntity teacherEntity;

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public AdministratorEntity getAdminBO() {
        return adminBO;
    }

    public void setAdminBO(AdministratorEntity adminBO) {
        this.adminBO = adminBO;
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     * 返回数据结构
     */
    public String  homePage(){
        try {
            if(userDao.checkLogin()){
                HttpServletRequest request= ServletActionContext.getRequest();
                HttpSession session=request.getSession();
                String phone=(String)session.getAttribute("phone");
                if(!StringUtils.isEmpty(phone)){
                    adminBO=new AdministratorEntity();
                    adminBO=userDao.findAdminByPhone(phone);
                    return "data";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    public String  teacherHomePage(){
        try {
            if(userDao.checkLogin()){
                HttpServletRequest request= ServletActionContext.getRequest();
                HttpSession session=request.getSession();
                String phone=(String)session.getAttribute("phone");
                if(!StringUtils.isEmpty(phone)){
                    teacherEntity=new TeacherEntity();
                    teacherEntity=userDao.findTeacherByPhone(phone);
                    return "data";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }


}
