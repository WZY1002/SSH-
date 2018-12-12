package com.pl.action;

import com.pl.dao.CalendarDao;
import com.pl.model.CalendarEntiy;
import com.pl.service.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.Locale;


//日历管理
@Controller
@Scope("prototype")
public class CalendarAction {
    private String title;
    private String start;
    private String end;
    private JsonResult jsonResult;

    @Resource
    private CalendarDao calendarDao;

    private List<CalendarEntiy> calendarEntiyList;

    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public List<CalendarEntiy> getCalendarEntiyList() {
        return calendarEntiyList;
    }

    public void setCalendarEntiyList(List<CalendarEntiy> calendarEntiyList) {
        this.calendarEntiyList = calendarEntiyList;
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        try {
            this.title =URLDecoder.decode(title,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        try {
            this.start =URLDecoder.decode(start,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        try {
            this.end =URLDecoder.decode(end,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String dispatcher(){
        return "data";
    }

    public String list(){
        list=calendarDao.list();
        return "data";
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            calendarDao.add(title,start,end);
            jsonResult.setState(true);
            return "success";
        }catch (Exception e){
            jsonResult.setMessage("添加校历事件信息失败");
            e.printStackTrace();
        }
        return "error";
    }
    public String update(){
        jsonResult=new JsonResult();
        try {
            calendarDao.update(start,end,title);
            jsonResult.setState(true);
            jsonResult.setMessage("修改成功");
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("修改失败");
            e.printStackTrace();


        }
        return "data";
    }

    public String delete(){
        jsonResult=new JsonResult();
        try {
            calendarDao.delete(start,end);
            jsonResult.setState(true);
            jsonResult.setMessage("删除成功");
        }catch (Exception e){
            jsonResult.setState(false);
            jsonResult.setMessage("删除失败");
            e.printStackTrace();

        }
        return "data";
    }

}
