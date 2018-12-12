package com.pl.action;

import com.pl.dao.TeacherDao;
import com.pl.dao.TeachingworkDao;
import com.pl.model.CopyrightEntity;
import com.pl.model.TeachingworkEntity;
import com.pl.service.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.*;

@Controller
@Scope("prototype")
public class TeachingworkAction {
    @Resource
    private TeachingworkDao teachingworkDao;

    private JsonResult jsonResult;
    private String pageSize;
    private String  pageNum;
    private Integer pageCount;
    private List<TeachingworkEntity> teachingworkEntityList;
    private String id;
    private String classid;
    private String courseid;
    private String  classhour;
    private String  classnumber;
    private String  starttime;
    private String endtime;
    private String classname;
    private String coursename;
    private String tid;
    private Integer index;
    private Integer status;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            this.name =  URLDecoder.decode(name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<TeachingworkEntity> getTeachingworkEntityList() {
        return teachingworkEntityList;
    }

    public void setTeachingworkEntityList(List<TeachingworkEntity> teachingworkEntityList) {
        this.teachingworkEntityList = teachingworkEntityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getClasshour() {
        return classhour;
    }

    public void setClasshour(String classhour) {
        this.classhour = classhour;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        try {
            this.starttime =  URLDecoder.decode(starttime,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        try {
            this.endtime =  URLDecoder.decode(endtime,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        try {
            this.coursename =  URLDecoder.decode(coursename,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //this.coursename = coursename;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            tid=(String) session.getAttribute("id");
            teachingworkDao.add(tid,classhour,classnumber,starttime,endtime,coursename,classname);
            jsonResult.setState(true);
            jsonResult.setMessage("添加工作方案成功");
            return "success";
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("添加工作方案失败");
            e.printStackTrace();
        }
        return "error";
    }

    public String listByTeacher(){
        int start = 0;
        int end=0;
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        id=(String)session.getAttribute("id");
        if(pageSize!=null && pageNum !=null){
            if (pageNum.equals("1"))
            {
                start = 1 - 1;
            }
            else
            {
                start = (Integer.parseInt(pageNum) - 1) * Integer.parseInt(pageSize);
            }
            end=start+Integer.parseInt(pageSize);
        }else{
            end=10;
            pageNum="1";
            pageSize="10";
        }
        int  total=teachingworkDao.ListCountByTeacher(id,coursename,starttime,endtime,name);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        teachingworkEntityList=new ArrayList<TeachingworkEntity>();
        teachingworkEntityList=teachingworkDao.listByTeacher(start,end,id,coursename,starttime,endtime,name);
        if(teachingworkEntityList!=null){
            int index=0;
            for (TeachingworkEntity teachingworkEntity:teachingworkEntityList) {
                index=index+1;
                teachingworkEntity.setIndex(index);
            }
        }
        return "data";
    }

    /**
     * 列表获取信息
     */
    public String list(){
        int start = 0;
        int end=0;
        if(pageSize!=null && pageNum !=null){
            if (pageNum.equals("1"))
            {
                start = 1 - 1;
            }
            else
            {
                start = (Integer.parseInt(pageNum) - 1) * Integer.parseInt(pageSize);
            }
            end=start+Integer.parseInt(pageSize);
        }else{
            end=10;
            pageNum="1";
            pageSize="10";
        }
        int  total=teachingworkDao.ListCount(coursename,starttime,endtime);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        teachingworkEntityList=new ArrayList<TeachingworkEntity>();
        teachingworkEntityList=teachingworkDao.list(start,end,coursename,starttime,endtime);
        int index=0;
        if(teachingworkEntityList!=null){
            for (TeachingworkEntity teachingworkEntity:teachingworkEntityList) {
                index=index+1;
                teachingworkEntity.setIndex(index);
            }
        }
        return "data";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(null!=(status)&&StringUtils.isEmpty(classhour)&&StringUtils.isEmpty(classnumber)&&StringUtils.isEmpty(starttime)&&StringUtils.isEmpty(endtime)&&StringUtils.isEmpty(coursename)&&StringUtils.isEmpty(classname)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            teachingworkDao.update(id,status,classhour,classnumber,starttime,endtime,coursename,classname);
            jsonResult.setState(true);
            jsonResult.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("修改状态失败");
        }
        return "data";
    }

    public String delete(){
        jsonResult=new JsonResult();
        try {
            teachingworkDao.Delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除工作计划失败");
        }
        return "data";
    }

}
