package com.pl.action;

import com.pl.dao.CopyrightDao;
import com.pl.model.CopyrightEntity;
import com.pl.model.PatentEntity;
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
public class CopyrightAction {
    @Resource
    private CopyrightDao copyrightDao;

    private JsonResult jsonResult;
    private String pageSize;
    private String  pageNum;
    private Integer pageCount;
    private List<CopyrightEntity> copyrightEntityList;
    private String id;
    private String workname;
    private String worktype;
    private String author;
    private String copyrighter;
    private String completetime;
    private String publishtime;
    private String registration;
    private String registid;
    private String access;
    private Integer status;
    private Integer index;
    private String tid;

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

    public List<CopyrightEntity> getCopyrightEntityList() {
        return copyrightEntityList;
    }

    public void setCopyrightEntityList(List<CopyrightEntity> copyrightEntityList) {
        this.copyrightEntityList = copyrightEntityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        try {
            this.workname =  URLDecoder.decode(workname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopyrighter() {
        return copyrighter;
    }

    public void setCopyrighter(String copyrighter) {
        this.copyrighter = copyrighter;
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getRegistid() {
        return registid;
    }

    public void setRegistid(String registid) {
        this.registid = registid;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            tid=(String) session.getAttribute("id");
            copyrightDao.add(tid,workname,author,worktype,copyrighter,completetime,publishtime,registration,registid,access);
            jsonResult.setState(true);
            jsonResult.setMessage("添加著作权成功");
            return "success";
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("添加著作权失败");
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
        int  total=copyrightDao.ListCountByTeacher(id,workname);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        copyrightEntityList=new ArrayList<CopyrightEntity>();
        copyrightEntityList=copyrightDao.listByTeacher(start,end,id,workname);
        if(copyrightEntityList!=null){
            int index=0;
            for (CopyrightEntity copyrightEntity:copyrightEntityList) {
                index=index+1;
                copyrightEntity.setIndex(index);
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
        int  total=copyrightDao.ListCount(workname);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        copyrightEntityList=new ArrayList<CopyrightEntity>();
        copyrightEntityList=copyrightDao.list(start,end,workname);
        if(copyrightEntityList!=null){
            int index=0;
            for (CopyrightEntity copyrightEntity:copyrightEntityList) {
                index=index+1;
                copyrightEntity.setIndex(index);
            }
        }
        return "data";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(null!=(status)&& StringUtils.isEmpty(workname)&&StringUtils.isEmpty(author)&&StringUtils.isEmpty(worktype)&&StringUtils.isEmpty(copyrighter)&&StringUtils.isEmpty(completetime)&&StringUtils.isEmpty(publishtime)&&StringUtils.isEmpty(registration)&&StringUtils.isEmpty(registid)&&StringUtils.isEmpty(access)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            copyrightDao.update(id,status,workname,author,worktype,copyrighter,completetime,publishtime,registration,registid,access);
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
            copyrightDao.Delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除失败");
        }
        return "data";
    }
}
