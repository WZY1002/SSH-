package com.pl.action;

import com.google.gson.Gson;
import com.pl.dao.UserDao;
import com.pl.model.AdministratorEntity;
import com.pl.model.MsgBO;
import com.pl.model.TeacherEntity;
import com.pl.service.Administrator;
import com.pl.service.JsonResult;
import com.pl.service.ResultUtils;
import com.pl.service.impl.AdministratorImpl;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Scope("prototype")
public class UserAction {
    @Resource
    private Administrator administrator;

    @Resource
    private UserDao userDao;

    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    private String phone;

    private String password;

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    管理员登录
    public String adminLogin(){
        jsonResult = new JsonResult();
        try {
            String result=administrator.adminLogin(phone,password);
            if(!StringUtils.isEmpty(result)){
                if("true".equals(result)){
                    HttpServletRequest request= ServletActionContext.getRequest();
                    HttpSession session=request.getSession();
                    session.setAttribute("phone",phone);
                    jsonResult.setState(true);
                }else{
                    jsonResult.setState(false);
                    jsonResult.setMessage(result);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

//    教师登录
    public String teacherLogin(){
        jsonResult = new JsonResult();
        try {
            String result=administrator.teacherLogin(phone,password);
            TeacherEntity teacherEntity=userDao.findTeacherByPhone(phone);
            if(!StringUtils.isEmpty(result)){
                if("true".equals(result)){
                    HttpServletRequest request= ServletActionContext.getRequest();
                    HttpSession session=request.getSession();
                    session.setAttribute("phone",phone);
                    session.setAttribute("id",teacherEntity.getId());
                    session.setAttribute("photo",teacherEntity.getPhoto());
                    jsonResult.setState(true);
                }else{
                    jsonResult.setState(false);
                    jsonResult.setMessage(result);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }
}
