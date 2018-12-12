package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.TeachingworkDao;
import com.pl.model.CopyrightEntity;
import com.pl.model.TeachingworkEntity;
import com.pl.model.ThesisEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@Repository
public class TeachingworkDaoImpl implements TeachingworkDao {
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

    public List<TeachingworkEntity> listByTeacher(int start, int end, String id, String coursename,String starttime,String endtime,String name) {
        try {
            String sql = "";
            List<TeachingworkEntity> list = new ArrayList();
            Connection conn = getConn();
            String where="Where status=1 AND  tid='"+id+"'";
            if (!StringUtils.isEmpty(coursename)){
                where=where+"and tw.coursename like '%" + coursename + "%'";
            }
            if (!StringUtils.isEmpty(starttime)){
                where=where+"and tw.starttime>='"+starttime+"'";
            }
            if (!StringUtils.isEmpty(endtime)){
                where=where+"and tw.endtime<='"+endtime+"'";
            }
            if (!StringUtils.isEmpty(name)){
                where=where+"and th.`name` like '%" + name + "%'";
            }
            //sql = "SELECT SUM(classhour) as classCount,starttime,endtime,coursename,status FROM teachingwork "+where+"  GROUP BY coursename,starttime,endtime limit " + start + "," + end + "  ";
            sql = "SELECT SUM(tw.classhour) as classCount,tw.starttime,tw.endtime,tw.coursename,tw.`status`,th.`name` FROM teachingwork tw JOIN teacher th ON tw.tid=th.id "+where+" GROUP BY coursename,starttime,endtime limit " + start + "," + end + "";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                TeachingworkEntity teachingworkEntity=new TeachingworkEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                teachingworkEntity=gson.fromJson((gson.toJson(rowData)),TeachingworkEntity.class);
                list.add(teachingworkEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCountByTeacher(String id, String coursename,String starttime,String endtime,String name) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            String where="Where tw.status=1 AND  tw.tid='"+id+"'";
            if (!StringUtils.isEmpty(coursename)){
                where=where+"and tw.coursename like '%" + coursename + "%'";
            }
            if (!StringUtils.isEmpty(starttime)){
                where=where+"and tw.starttime>='"+starttime+"'";
            }
            if (!StringUtils.isEmpty(endtime)){
                where=where+"and tw.endtime<='"+endtime+"'";
            }
            if (!StringUtils.isEmpty(name)){
                where=where+"and th.`name` like '%" + name + "%'";
            }
            //sql = "SELECT SUM(classhour) as classCount,starttime,endtime,coursename FROM teachingwork "+where+"  GROUP BY coursename,starttime,endtime";
            sql = "SELECT SUM(tw.classhour) as classCount,tw.starttime,tw.endtime,tw.coursename,tw.`status`,th.`name` FROM teachingwork tw JOIN teacher th ON tw.tid=th.id "+where+" GROUP BY coursename,starttime,endtime";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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

    public void add(String tid, String classhour, String classnumber, String starttime, String endtime, String coursename, String classname) {
        TeachingworkEntity teachingworkEntity=new TeachingworkEntity();
        if (!StringUtils.isEmpty(tid)){
            teachingworkEntity.setTid(tid);
        }
        if (!StringUtils.isEmpty(classhour)){
            teachingworkEntity.setClasshour(classhour);
        }
        if (!StringUtils.isEmpty(classnumber)){
            teachingworkEntity.setClassnumber(classnumber);
        }
        if (!StringUtils.isEmpty(starttime)){
            teachingworkEntity.setStarttime(starttime);
        }
        if (!StringUtils.isEmpty(endtime)){
            teachingworkEntity.setEndtime(endtime);
        }
        if (!StringUtils.isEmpty(coursename)){
            teachingworkEntity.setCoursename(coursename);
        }
        if (!StringUtils.isEmpty(classname)){
            teachingworkEntity.setClassname(classname);
        }
        teachingworkEntity.setId(UUID.randomUUID().toString());
        teachingworkEntity.setStatus(0);
        template.save(teachingworkEntity);
    }

    public List<TeachingworkEntity> list(int start, int end,String coursename,String starttime,String endtime) {
        try {
            String sql = "";
            List<TeachingworkEntity> list = new ArrayList();
            Connection conn = getConn();
            String where="Where 1=1 ";
            if (!StringUtils.isEmpty(coursename)){
                where=where+"and coursename like '%" + coursename + "%'";
            }
            if (!StringUtils.isEmpty(starttime)){
                where=where+"and starttime>='"+starttime+"'";
            }
            if (!StringUtils.isEmpty(endtime)){
                where=where+"and endtime<='"+endtime+"'";
            }
            sql = "select th.*,te.`name` AS teacherName from teachingwork th LEFT JOIN teacher te ON th.tid=te.id "+where+" ORDER by status limit " + start + "," + end + "";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                TeachingworkEntity teachingworkEntity=new TeachingworkEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                teachingworkEntity=gson.fromJson((gson.toJson(rowData)),TeachingworkEntity.class);
                list.add(teachingworkEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCount(String coursename,String starttime,String endtime) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            String where="Where 1=1 ";
            if (!StringUtils.isEmpty(coursename)){
                where=where+"and coursename like '%" + coursename + "%'";
            }
            if (!StringUtils.isEmpty(starttime)){
                where=where+"and starttime>'"+starttime+"'";
            }
            if (!StringUtils.isEmpty(endtime)){
                where=where+"and endtime<'"+endtime+"'";
            }
            sql = "select * from teachingwork "+where+"";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
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

    public void update(String id, Integer status,String classhour,String classnumber,String starttime,String endtime,String coursename,String classname) {
        TeachingworkEntity teachingworkEntity = (TeachingworkEntity) template.get(TeachingworkEntity.class, id);
        if (null!=status){
            teachingworkEntity.setStatus(status);
        }else{
            teachingworkEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(classhour)){
            teachingworkEntity.setClasshour(classhour);
        }
        if (!StringUtils.isEmpty(classnumber)){
            teachingworkEntity.setClassnumber(classnumber);
        }
        if (!StringUtils.isEmpty(starttime)){
            teachingworkEntity.setStarttime(starttime);
        }
        if (!StringUtils.isEmpty(endtime)){
            teachingworkEntity.setEndtime(endtime);
        }
        if (!StringUtils.isEmpty(coursename)){
            teachingworkEntity.setCoursename(coursename);
        }
        if (!StringUtils.isEmpty(classname)){
            teachingworkEntity.setClassname(classname);
        }
        template.update(teachingworkEntity);
    }

    /**
     * 删除
     */
    public void Delete(String id) {
            TeachingworkEntity teachingworkEntity=(TeachingworkEntity) template.load(TeachingworkEntity.class,id);
            template.delete(teachingworkEntity);
    }
}
