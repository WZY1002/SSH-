package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.ProjectDao;
import com.pl.model.ProjectEntity;
import com.pl.model.TeachingworkEntity;
import com.pl.model.ThesisEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@Repository
public class ProjectDaoImpl implements ProjectDao {
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

    public List<ProjectEntity> list(int start, int end, String name) {
        try {
            String sql = "";
            List<ProjectEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select th.*,te.`name` AS teacherName from project th LEFT JOIN teacher te ON th.tid=te.id ORDER by status limit " + start + "," + end + "";
            } else {
                sql = "select th.*,te.`name` AS teacherName from project th LEFT JOIN teacher te ON th.tid=te.id WHERE th.projectname like '%" + name + "%' ORDER BY status  limit " + start + "," + end + "";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                ProjectEntity projectEntity=new ProjectEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                projectEntity=gson.fromJson((gson.toJson(rowData)),ProjectEntity.class);
                list.add(projectEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCount(String name) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select * from project";
            } else {
                sql = "select * from project WHERE projectname like '%" + name + "%'";
            }
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

    public List<ProjectEntity> listByTeacher(int start, int end, String id, String name) {
        try {
            String sql = "";
            List<ProjectEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select * from project  WHERE 1=1 AND tid='"+id+"' ORDER  BY status limit " + start + "," + end + " ";
            } else {
                sql = "select * from project WHERE tid='"+id+"' AND projectname like '%" + name + "%' ORDER  BY status limit " + start + "," + end + "  ";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                ProjectEntity projectEntity=new ProjectEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                projectEntity=gson.fromJson((gson.toJson(rowData)),ProjectEntity.class);
                list.add(projectEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCountByTeacher(String id, String name) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select * from project  WHERE 1=1 AND tid='"+id+"' ORDER  BY status ";
            } else {
                sql = "select * from project WHERE tid='"+id+"' AND projectname like '%" + name + "%' ORDER  BY status ";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                total=total+1;
            }
            conn.close();;
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(String tid,String type,String projectname,String agelimit,String money,String level,String authors) {
        ProjectEntity projectEntity=new ProjectEntity();
        if (!StringUtils.isEmpty(tid)){
            projectEntity.setTid(tid);
        }
        if (!StringUtils.isEmpty(type)){
            projectEntity.setType(type);
        }
        if (!StringUtils.isEmpty(projectname)){
            projectEntity.setProjectname(projectname);
        }
        if (!StringUtils.isEmpty(agelimit)){
            projectEntity.setAgelimit(agelimit);
        }
        if (!StringUtils.isEmpty(money)){
            projectEntity.setMoney(money);
        }
        if (!StringUtils.isEmpty(level)){
            projectEntity.setLevel(level);
        }
        if (!StringUtils.isEmpty(authors)){
            projectEntity.setAuthors(authors);
        }
        projectEntity.setId(UUID.randomUUID().toString());
        projectEntity.setStatus(0);
        template.save(projectEntity);
    }

    public void update(String id, Integer status,String type,String projectname,String agelimit,String money,String level,String authors) {
        ProjectEntity projectEntity = (ProjectEntity) template.get(ProjectEntity.class, id);
        if (null!=status){
            projectEntity.setStatus(status);
        }else {
            projectEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(type)){
            projectEntity.setType(type);
        }
        if (!StringUtils.isEmpty(projectname)){
            projectEntity.setProjectname(projectname);
        }
        if (!StringUtils.isEmpty(agelimit)){
            projectEntity.setAgelimit(agelimit);
        }
        if (!StringUtils.isEmpty(money)){
            projectEntity.setMoney(money);
        }if (!StringUtils.isEmpty(level)){
            projectEntity.setLevel(level);
        }
        if (!StringUtils.isEmpty(authors)){
            projectEntity.setAuthors(authors);
        }
        template.update(projectEntity);
    }
    /**
     * 删除
     */
    public void Delete(String id) {
        ProjectEntity projectEntity=(ProjectEntity) template.load(ProjectEntity.class,id);
        template.delete(projectEntity);
    }
}
