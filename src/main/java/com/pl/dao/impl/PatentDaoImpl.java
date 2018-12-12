package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.PatentDao;
import com.pl.model.PatentEntity;
import com.pl.model.ProjectEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@Repository
public class PatentDaoImpl implements PatentDao {
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

    public List<PatentEntity> list(int start, int end, String patentname) {
        try {
            String sql = "";
            List<PatentEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(patentname)) {
                sql = "select th.*,te.`name` AS teacherName from patent th LEFT JOIN teacher te ON th.tid=te.id ORDER by status limit " + start + "," + end + "";
            } else {
                sql = "select th.*,te.`name` AS teacherName from patent th LEFT JOIN teacher te ON th.tid=te.id WHERE th.patentname like '%" + patentname + "%' ORDER BY status  limit " + start + "," + end + "";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                PatentEntity patentEntity=new PatentEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                patentEntity=gson.fromJson((gson.toJson(rowData)),PatentEntity.class);
                list.add(patentEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCount(String patentname) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(patentname)) {
                sql = "select * from patent";
            } else {
                sql = "select * from patent WHERE patentname like '%" + patentname + "%'";
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

    public List<PatentEntity> listByTeacher(int start, int end, String id, String name) {
        try {
            String sql = "";
            List<PatentEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(name)) {
                sql = "select * from patent  WHERE 1=1 AND tid='"+id+"' ORDER  BY status limit " + start + "," + end + " ";
            } else {
                sql = "select * from patent WHERE tid='"+id+"' AND patentname like '%" + name + "%' ORDER  BY status limit " + start + "," + end + "  ";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                PatentEntity patentEntity=new PatentEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                patentEntity=gson.fromJson((gson.toJson(rowData)),PatentEntity.class);
                list.add(patentEntity);
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
                sql = "select * from patent  WHERE 1=1 AND tid='"+id+"' ORDER  BY status ";
            } else {
                sql = "select * from patent WHERE tid='"+id+"' AND patentname like '%" + name + "%' ORDER  BY status ";
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

    public void add(String tid, String patentid,String applicationtime, String name, String author, String authorizeder, String noticeday) {
        PatentEntity patentEntity=new PatentEntity();
        if (!StringUtils.isEmpty(tid)){
            patentEntity.setTid(tid);
        }
        if (!StringUtils.isEmpty(patentid)){
            patentEntity.setPatentid(patentid);
        }
        if (!StringUtils.isEmpty(applicationtime)){
            patentEntity.setApplicationtime(applicationtime);
        }
        if (!StringUtils.isEmpty(name)){
            patentEntity.setPatentname(name);
        }
        if (!StringUtils.isEmpty(author)){
            patentEntity.setAuthor(author);
        }
        if (!StringUtils.isEmpty(authorizeder)){
            patentEntity.setAuthorizeder(authorizeder);
        }
        if (!StringUtils.isEmpty(noticeday)){
            patentEntity.setNoticeday(noticeday);
        }
        patentEntity.setId(UUID.randomUUID().toString());
        patentEntity.setStatus(0);
        template.save(patentEntity);
    }

    public void update(String id, Integer status,String patentid,String author,String patentname,String applicationtime,String authorizeder,String noticeday) {
        PatentEntity patentEntity = (PatentEntity) template.get(PatentEntity.class, id);
        if (null!=status){
            patentEntity.setStatus(status);
        }else {
            patentEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(patentid)){
            patentEntity.setPatentid(patentid);
        }
        if (!StringUtils.isEmpty(author)){
            patentEntity.setAuthor(author);
        }
        if (!StringUtils.isEmpty(patentname)){
            patentEntity.setPatentname(patentname);
        }
        if (!StringUtils.isEmpty(applicationtime)){
            patentEntity.setApplicationtime(applicationtime);
        }
        if (!StringUtils.isEmpty(authorizeder)){
            patentEntity.setAuthorizeder(authorizeder);
        }
        if (!StringUtils.isEmpty(noticeday)){
            patentEntity.setNoticeday(noticeday);
        }
        template.update(patentEntity);
    }

    /**
     * 删除
     */
    public void Delete(String id) {
        PatentEntity patentEntity=(PatentEntity) template.load(PatentEntity.class,id);
        template.delete(patentEntity);
    }
}
