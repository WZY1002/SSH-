package com.pl.action;

import com.pl.dao.PatentDao;
import com.pl.model.PatentEntity;
import com.pl.model.ProjectEntity;
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
public class PatentAction {
    @Resource
    private PatentDao patentDao;

    private JsonResult jsonResult;
    private String pageSize;
    private String  pageNum;
    private Integer pageCount;
    private List<PatentEntity> patentEntityList;
    private String id;
    private String patentid;
    private String author;
    private String name;
    private String  applicationtime;
    private String authorizeder;
    private String  noticeday;
    private Integer status;
    private String tid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatentid() {
        return patentid;
    }

    public void setPatentid(String patentid) {
        try {
            this.patentid =  URLDecoder.decode(patentid,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        this.patentid = patentid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        try {
            this.author =  URLDecoder.decode(author,"UTF-8");
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

    public String getApplicationtime() {
        return applicationtime;
    }

    public void setApplicationtime(String applicationtime) {
        try {
            this.applicationtime =  URLDecoder.decode(applicationtime,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getAuthorizeder() {
        return authorizeder;
    }

    public void setAuthorizeder(String authorizeder) {
        try {
            this.authorizeder =  URLDecoder.decode(authorizeder,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String patentname;

    public String getPatentname() {
        return patentname;
    }

    public void setPatentname(String patentname) {
        try {
            this.patentname =  URLDecoder.decode(patentname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getNoticeday() {
        return noticeday;
    }

    public void setNoticeday(String noticeday) {
        try {
            this.noticeday =  URLDecoder.decode(noticeday,"UTF-8");
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
        this.tid = tid;
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

    public List<PatentEntity> getPatentEntityList() {
        return patentEntityList;
    }

    public void setPatentEntityList(List<PatentEntity> patentEntityList) {
        this.patentEntityList = patentEntityList;
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            tid=(String) session.getAttribute("id");
            patentDao.add(tid,patentid,applicationtime,name,author,authorizeder,noticeday);
            jsonResult.setState(true);
            jsonResult.setMessage("添加专利成功");
            return "success";
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("添加专利失败");
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
        int  total=patentDao.ListCountByTeacher(id,name);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        patentEntityList=new ArrayList<PatentEntity>();
        patentEntityList=patentDao.listByTeacher(start,end,id,name);
        if(patentEntityList!=null){
            int index=0;
            for (PatentEntity patentEntity:patentEntityList) {
                index=index+1;
                patentEntity.setIndex(index);
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
        int  total=patentDao.ListCount(name);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        patentEntityList=new ArrayList<PatentEntity>();
        patentEntityList=patentDao.list(start,end,name);
        if(patentEntityList!=null){
            int index=0;
            for (PatentEntity patentEntity:patentEntityList) {
                index=index+1;
                patentEntity.setIndex(index);
            }
        }
        return "data";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(null!=(status)&& StringUtils.isEmpty(patentid)&&StringUtils.isEmpty(author)&&StringUtils.isEmpty(patentname)&&StringUtils.isEmpty(applicationtime)&&StringUtils.isEmpty(authorizeder)&&StringUtils.isEmpty(noticeday)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            patentDao.update(id,status,patentid,author,patentname,applicationtime,authorizeder,noticeday);
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
            patentDao.Delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除失败");
        }
        return "data";
    }

}
