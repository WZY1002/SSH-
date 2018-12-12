package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.TeacherDao;
import com.pl.model.AdministratorEntity;
import com.pl.model.TeacherEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.util.StringUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Resource(name = "hibernateTemplate")
    private HibernateTemplate template;

    private Gson gson = new Gson();

    /**
     * 传统jdbc
     */
    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/pl";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
//条件获取教师列表
    public List<TeacherEntity> list(int start, int end, String name) {
        try {
            String sql = "";
            List<TeacherEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select * from teacher WHERE 1=1 limit " + start + "," + end + "";
            } else {
                sql = "select * from teacher WHERE name like '%" + name + "%' limit " + start + "," + end + "";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                TeacherEntity teacherEntity=new TeacherEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                teacherEntity=gson.fromJson((gson.toJson(rowData)),TeacherEntity.class);
                list.add(teacherEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//获取教师列表条数
    public int ListCount(String name) {
        int total=0;
        try {
            String sql = "";
            List<TeacherEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select * from teacher";
            } else {
                sql = "select * from teacher WHERE name like '%" + name + "%'";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
               total=total+1;
            }
            conn.close();
          return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void Delete(String id) {
        //删除老师
        TeacherEntity teacherEntity=(TeacherEntity) template.load(TeacherEntity.class,id);
        template.delete(teacherEntity);
        //删除老师所有相关信息
        Connection conn = getConn();
        // 获取PreparedStatement
        String copyrightsql = "DELETE FROM copyright WHERE tid = "+id+"";
        String monographssql = "DELETE FROM monographs WHERE tid = "+id+"";
        String patentsql = "DELETE FROM patent WHERE tid = "+id+"";
        String projectsql = "DELETE FROM project WHERE tid = "+id+"";
        String teachingworksql = "DELETE FROM teachingwork WHERE tid = "+id+"";
        String thesissql = "DELETE FROM thesis WHERE tid = "+id+"";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(copyrightsql);
            int del1=pstmt.executeUpdate();
            pstmt = conn.prepareStatement(monographssql);
            int del2=pstmt.executeUpdate();
            pstmt = conn.prepareStatement(patentsql);
            int del3=pstmt.executeUpdate();
            pstmt = conn.prepareStatement(projectsql);
            int del4=pstmt.executeUpdate();
            pstmt = conn.prepareStatement(teachingworksql);
            int del5=pstmt.executeUpdate();
            pstmt = conn.prepareStatement(thesissql);
            int del6=pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void update(String id,String name,String password,String nation,String birth,String place,String address,String mobile_phone,String home_phone,String identity,String education,String school,String politics,String healthy,String department,String photo) {
        TeacherEntity teacherEntity = (TeacherEntity)template.get(TeacherEntity.class, id);
        if (!StringUtils.isEmpty(name)){
            teacherEntity.setName(name);
        }
        if (!StringUtils.isEmpty(password)){
            teacherEntity.setPassword(password);
        }
        if (!StringUtils.isEmpty(nation)){
            teacherEntity.setNation(nation);
        }
        if (!StringUtils.isEmpty(birth)){
            teacherEntity.setBirth(birth);
        }
        if (!StringUtils.isEmpty(place)){
            teacherEntity.setPlace(place);
        }
        if (!StringUtils.isEmpty(address)){
            teacherEntity.setAddress(address);
        }
        if (!StringUtils.isEmpty(mobile_phone)){
            teacherEntity.setMobile_phone(mobile_phone);
        }
        if (!StringUtils.isEmpty(home_phone)){
            teacherEntity.setHome_phone(home_phone);
        }
        if (!StringUtils.isEmpty(identity)){
            teacherEntity.setIdentity(identity);
        }
        if (!StringUtils.isEmpty(education)){
            teacherEntity.setEducation(education);
        }
        if (!StringUtils.isEmpty(school)){
            teacherEntity.setSchool(school);
        }
        if (!StringUtils.isEmpty(politics)){
            teacherEntity.setPolitics(politics);
        }
        if (!StringUtils.isEmpty(healthy)){
            teacherEntity.setHealthy(healthy);
        }
        if (!StringUtils.isEmpty(department)){
            teacherEntity.setDepartment(department);
        }
        if (!StringUtils.isEmpty(photo)){
            teacherEntity.setPhoto(photo);
        }
        template.update(teacherEntity);
    }

    public void add(String id, String name, String password, String nation, String birth, String place, String address, String mobile_phone, String home_phone, String identity, String education, String school,String politics,String healthy) {
        TeacherEntity teacherEntity = new TeacherEntity();
        if (!StringUtils.isEmpty(name)){
            teacherEntity.setName(name);
        }
        if (!StringUtils.isEmpty(password)){
            teacherEntity.setPassword(password);
        }
        if (!StringUtils.isEmpty(nation)){
            teacherEntity.setNation(nation);
        }
        if (!StringUtils.isEmpty(birth)){
            teacherEntity.setBirth(birth);
        }
        if (!StringUtils.isEmpty(place)){
            teacherEntity.setPlace(place);
        }
        if (!StringUtils.isEmpty(address)){
            teacherEntity.setAddress(address);
        }
        if (!StringUtils.isEmpty(mobile_phone)){
            teacherEntity.setMobile_phone(mobile_phone);
        }
        if (!StringUtils.isEmpty(home_phone)){
            teacherEntity.setHome_phone(home_phone);
        }
        if (!StringUtils.isEmpty(identity)){
            teacherEntity.setIdentity(identity);
        }
        if (!StringUtils.isEmpty(education)){
            teacherEntity.setEducation(education);
        }
        if (!StringUtils.isEmpty(school)){
            teacherEntity.setSchool(school);
        }
        if (!StringUtils.isEmpty(politics)){
            teacherEntity.setPolitics(politics);
        }
        if (!StringUtils.isEmpty(healthy)){
            teacherEntity.setHealthy(healthy);
        }
        Date day=new Date();
        teacherEntity.setEmploy_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        teacherEntity.setId(UUID.randomUUID().toString());
        template.save(teacherEntity);
    }
//    获取某教师数据
    public TeacherEntity teacherInfo(String id) {
        TeacherEntity teacherEntity = (TeacherEntity)template.get(TeacherEntity.class, id);
        return teacherEntity;
    }

}
