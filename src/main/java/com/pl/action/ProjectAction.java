package com.pl.action;

import com.pl.dao.ProjectDao;
import com.pl.model.ProjectEntity;
import com.pl.model.ThesisEntity;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class ProjectAction {
    @Resource
    private ProjectDao projectDao;

    private JsonResult jsonResult;
    private String pageSize;
    private String  pageNum;
    private Integer pageCount;
    private String id;
    private String type;
    private String name;
    private String agelimit;
    private String money;
    private String level;
    private String authors;
    private Integer status;
    private String tid;
    private String projectname;

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        try {
            this.projectname =  URLDecoder.decode(projectname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        try {
            this.id =  URLDecoder.decode(id,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        try {
            this.type =  URLDecoder.decode(type,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

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

    public String getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(String agelimit) {
        try {
            this.agelimit =  URLDecoder.decode(agelimit,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        try {
            this.money =  URLDecoder.decode(money,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        try {
            this.authors =  URLDecoder.decode(authors,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        try {
            this.tid =  URLDecoder.decode(tid,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private List<ProjectEntity> projectEntityList;

    public List<ProjectEntity> getProjectEntityList() {
        return projectEntityList;
    }

    public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
        this.projectEntityList = projectEntityList;
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     * 列表获取论文信息
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
        int  total=projectDao.ListCount(name);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        projectEntityList=new ArrayList<ProjectEntity>();
        projectEntityList=projectDao.list(start,end,name);
        if(projectEntityList!=null){
            int index=0;
            for (ProjectEntity projectEntity:projectEntityList) {
                index=index+1;
                projectEntity.setIndex(index);
            }
        }
        return "data";
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
        int  total=projectDao.ListCountByTeacher(id,name);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        projectEntityList=new ArrayList<ProjectEntity>();
        projectEntityList=projectDao.listByTeacher(start,end,id,name);
        if(projectEntityList!=null){
            int index=0;
            for (ProjectEntity projectEntity:projectEntityList) {
                index=index+1;
                projectEntity.setIndex(index);
            }
        }
        return "data";
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            tid=(String) session.getAttribute("id");
            projectDao.add(tid,type,name,agelimit,money,level,authors);
            jsonResult.setState(true);
            jsonResult.setMessage("添加项目信息成功");
            return "success";
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("添加项目信息失败");
            e.printStackTrace();
        }
        return "error";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(null!=(status)&& StringUtils.isEmpty(type)&&StringUtils.isEmpty(projectname)&&StringUtils.isEmpty(agelimit)&&StringUtils.isEmpty(money)&&StringUtils.isEmpty(level)&&StringUtils.isEmpty(authors)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            projectDao.update(id,status,type,projectname,agelimit,money,level,authors);
            jsonResult.setState(true);
            jsonResult.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("操作失败");
        }
        return "data";
    }

    public String delete(){
        jsonResult=new JsonResult();
        try {
            projectDao.Delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除项目失败");
        }
        return "data";
    }
}
