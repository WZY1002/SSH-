package com.pl.action;

import com.pl.dao.TeacherDao;
import com.pl.dao.UserDao;
import com.pl.dao.impl.UserDaoImpl;
import com.pl.model.PageInfoBO;
import com.pl.model.TeacherEntity;
import com.pl.service.JsonResult;
import com.pl.service.TeacherService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("prototype")
public class TeacherAction {
    @Resource
    private TeacherService  teacherService;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private UserDao userDao;
    private Integer pageCount;

    private PageInfoBO pageInfoBO;

    public PageInfoBO getPageInfoBO() {
        return pageInfoBO;
    }

    public void setPageInfoBO(PageInfoBO pageInfoBO) {
        this.pageInfoBO = pageInfoBO;
    }

    private List<TeacherEntity> teacherBOList;

    public List<TeacherEntity> getTeacherBOList() {
        return teacherBOList;
    }

    public void setTeacherBOList(List<TeacherEntity> teacherBOList) {
        this.teacherBOList = teacherBOList;
    }

    private JsonResult jsonResult;

    private String pageSize;
    private String  pageNum;
    private String name;
    private String id;
    private String cardId;
    private String sex;
    private String photo;
    private String nation;
    private String birth;
    private String place;
    private String politics;
    private String healthy;
    private String address;
    private String mobile_phone;
    private String home_phone;
    private String identity;
    //private String employ_time;
    private String education;
    private String school;
    private String department;
    private String password;

    //private ImgServlet imgServlet；
    //图片详细
//    private String  detailed;
//    // 封装上传文件域的属性
//    private File file;
//    // 封装上传文件类型的属性
//    private String fileFileName;
//    // 封装上传文件名的属性
//    private String fileContentType;
    private File headImg;
    private String headImgFileName;
    private String headImgContentType;

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    private TeacherEntity teacherEntity;

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        try {
            this.cardId =  URLDecoder.decode(cardId,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        try {
            this.sex =  URLDecoder.decode(sex,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        try {
            this.nation =  URLDecoder.decode(nation,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        try {
            this.birth =  URLDecoder.decode(birth,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        try {
            this.place =  URLDecoder.decode(place,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        try {
            this.politics =  URLDecoder.decode(politics,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        try {
            this.healthy =  URLDecoder.decode(healthy,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        try {
            this.address =  URLDecoder.decode(address,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        try {
            this.mobile_phone =  URLDecoder.decode(mobile_phone,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        try {
            this.home_phone =  URLDecoder.decode(home_phone,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        try {
            this.identity =  URLDecoder.decode(identity,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        try {
            this.education =  URLDecoder.decode(education,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        try {
            this.school =  URLDecoder.decode(school,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        try {
            this.department =  URLDecoder.decode(department,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            this.password =  URLDecoder.decode(password,"UTF-8");
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

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }
    @Resource(name = "hibernateTemplate")
    private HibernateTemplate template;


    /**
     * 列表获取教师信息
     */
    public String list(){
        //String pageNumset=pageNum;
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
            int  total=teacherService.listCount(name);
            pageCount = (total + Integer.parseInt(pageSize) - 1) / Integer.parseInt(pageSize);//总页数
            teacherBOList=new ArrayList<TeacherEntity>();
            teacherBOList=teacherService.list(start,end,name);
            return "data";
    }

    public String add(){
        jsonResult=new JsonResult();
        try {
            teacherDao.add(id,name,password,nation,birth,place,address,mobile_phone,home_phone,identity,education,school,politics,healthy);
            jsonResult.setMessage("添加教师信息成功");
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    public String delete(){
        jsonResult=new JsonResult();
        try {
            teacherService.delete(id);
            jsonResult.setState(true);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("删除教师失败");
        }
        return "data";
    }

    public String update(){
        jsonResult=new JsonResult();
        try {
            if(StringUtils.isEmpty(name)&&StringUtils.isEmpty(password)&&StringUtils.isEmpty(nation)&&StringUtils.isEmpty(identity)&&StringUtils.isEmpty(place)&&StringUtils.isEmpty(address)&&StringUtils.isEmpty(education)&&StringUtils.isEmpty(politics)&&StringUtils.isEmpty(school)&&StringUtils.isEmpty(department)){
                jsonResult.setState(false);
                jsonResult.setMessage("不能全为空");
                return "data";
            }
            teacherDao.update(id,name,password,nation,birth,place,address,mobile_phone,home_phone,identity,education,school,politics,healthy,department,photo);
            jsonResult.setState(true);
            jsonResult.setMessage("修改个人信息成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setMessage("修改个人信息失败");
        }
        return "data";
    }

    public String uploadImg(){
        jsonResult=new JsonResult();
        File destFile = null;
        //获得图片保存的路径
        String filePath = ServletActionContext.getServletContext().getRealPath("/images");
        String filename= UUID.randomUUID().toString()+headImgFileName.substring(headImgFileName.lastIndexOf("."));
        try {
            FileUtils.copyFile(headImg, new File(filePath,filename));
            teacherDao.update(id,name,password,nation,birth,place,address,mobile_phone,home_phone,identity,education,school,politics,healthy,department,filename);
            jsonResult.setState(true);
            jsonResult.setMessage("上传照片成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setState(false);
            jsonResult.setMessage("上传照片失败");

        }
        return "success";
}

//    获取单条教师信
    public String teacherInfo(){
        teacherEntity=new TeacherEntity();
        mobile_phone= (String) ServletActionContext.getRequest().getSession().getAttribute("phone");
        teacherEntity=userDao.findTeacherByPhone(mobile_phone);
        return "data";
    }

//    校历
    public String calendar(){
        return "data";
    }
}
