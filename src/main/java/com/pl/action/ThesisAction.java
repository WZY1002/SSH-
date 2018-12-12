package com.pl.action;

import com.pl.dao.ThesisDao;
import com.pl.model.TeacherEntity;
import com.pl.model.ThesisEntity;
import com.pl.service.JsonResult;
import com.pl.service.ThesisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class ThesisAction {
    @Resource
    private ThesisDao thesisDao;
    @Resource
    private ThesisService thesisService;

    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }
    private String pageSize;
    private String  pageNum;
    private Integer pageCount;
    private String title;
    private Integer status;
    private String id;
    private String published_time;
    private String magazine_name;
    private String authors;

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getMagazine_name() {
        return magazine_name;
    }

    public void setMagazine_name(String magazine_name) {
        this.magazine_name = magazine_name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public List<ThesisEntity> getThesisEntityList() {
        return thesisEntityList;
    }

    public void setThesisEntityList(List<ThesisEntity> thesisEntityList) {
        this.thesisEntityList = thesisEntityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private List<ThesisEntity> thesisEntityList;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        try {
            this.title =  URLDecoder.decode(title,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        int  total=thesisService.listCount(title);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        thesisEntityList=new ArrayList<ThesisEntity>();
        thesisEntityList=thesisService.list(start,end,title);
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
        int  total=thesisDao.ListCountByTeacher(id,title);
        pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
        thesisEntityList=new ArrayList<ThesisEntity>();
        thesisEntityList=thesisDao.listByTeacher(start,end,id,title);
        if(thesisEntityList!=null){
            int index=0;
            for (ThesisEntity thesisEntity:thesisEntityList) {
                index=index+1;
                thesisEntity.setIndex(index);
            }
        }
        return "data";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(null!=(status)&&StringUtils.isEmpty(title)&&StringUtils.isEmpty(published_time)&&StringUtils.isEmpty(magazine_name)&&StringUtils.isEmpty(authors)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            thesisDao.update(id,status,title,published_time,magazine_name,authors);
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
            thesisService.delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除论文失败");
        }
        return "data";
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            id=(String) session.getAttribute("id");
            thesisDao.add(id,title,published_time,magazine_name,authors);
            jsonResult.setState(true);
            jsonResult.setMessage("添加论文信息成功");
            return "success";
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("添加论文信息失败");
            e.printStackTrace();
        }
        return "error";
    }
}
