package com.pl.action;

import com.pl.dao.MonographsDao;
import com.pl.model.CopyrightEntity;
import com.pl.model.MonographsEntity;
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
public class MonographsAction {
    @Resource
    private MonographsDao monographsDao;

    private JsonResult jsonResult;
    private String pageSize;
    private String  pageNum;
    private Integer pageCount;
    private List<MonographsEntity> monographsEntityList;
    private String id;
    private String monographsname;
    private String author;
    private String publishtime;
    private String completetime;
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

    public List<MonographsEntity> getMonographsEntityList() {
        return monographsEntityList;
    }

    public void setMonographsEntityList(List<MonographsEntity> monographsEntityList) {
        this.monographsEntityList = monographsEntityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonographsname() {
        return monographsname;
    }

    public void setMonographsname(String monographsname) {
        try {
            this.monographsname =  URLDecoder.decode(monographsname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
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
            monographsDao.add(tid,monographsname,author,publishtime,completetime);
            jsonResult.setState(true);
            jsonResult.setMessage("添加专著成功");
            return "success";
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("添加专著失败");
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
        int  total=monographsDao.ListCountByTeacher(id,monographsname);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        monographsEntityList=new ArrayList<MonographsEntity>();
        monographsEntityList=monographsDao.listByTeacher(start,end,id,monographsname);
        if(monographsEntityList!=null){
            int index=0;
            for (MonographsEntity monographsEntity:monographsEntityList) {
                index=index+1;
                monographsEntity.setIndex(index);
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
        int  total=monographsDao.ListCount(monographsname);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        monographsEntityList=new ArrayList<MonographsEntity>();
        monographsEntityList=monographsDao.list(start,end,monographsname);
        if(monographsEntityList!=null){
            int index=0;
            for (MonographsEntity monographsEntity:monographsEntityList) {
                index=index+1;
                monographsEntity.setIndex(index);
            }
        }
        return "data";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(null!=(status)&& StringUtils.isEmpty(monographsname)&&StringUtils.isEmpty(author)&&StringUtils.isEmpty(publishtime)&&StringUtils.isEmpty(completetime)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            monographsDao.update(id,status,monographsname,author,publishtime,completetime);
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
            monographsDao.Delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除失败");
        }
        return "data";
    }
}
