package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.MonographsDao;
import com.pl.model.CopyrightEntity;
import com.pl.model.MonographsEntity;
import com.pl.model.PatentEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@Repository
public class MonographsDaoImpl implements MonographsDao {

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

    public List<MonographsEntity> listByTeacher(int start, int end, String id, String monographsname) {
        try {
            String sql = "";
            List<MonographsEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(monographsname)) {
                sql = "select * from monographs  WHERE 1=1 AND tid='"+id+"' ORDER  BY status limit " + start + "," + end + " ";
            } else {
                sql = "select * from monographs WHERE tid='"+id+"' AND monographs.monographsname like '%" + monographsname + "%' ORDER  BY status limit " + start + "," + end + "  ";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                MonographsEntity monographsEntity=new MonographsEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                monographsEntity=gson.fromJson((gson.toJson(rowData)),MonographsEntity.class);
                list.add(monographsEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCountByTeacher(String id, String monographsname) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(monographsname)) {
                sql = "select * from monographs  WHERE 1=1 AND tid='"+id+"' ORDER  BY status ";
            } else {
                sql = "select * from monographs WHERE tid='"+id+"' AND monographs.monographsname like '%" + monographsname + "%' ORDER  BY status ";
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

    public void add(String tid, String monographsname,String author, String publishtime, String completetime) {
        MonographsEntity monographsEntity=new MonographsEntity();
        if (!StringUtils.isEmpty(tid)){
            monographsEntity.setTid(tid);
        }
        if (!StringUtils.isEmpty(monographsname)){
            monographsEntity.setMonographsname(monographsname);
        }
        if (!StringUtils.isEmpty(author)){
            monographsEntity.setAuthor(author);
        }
        if (!StringUtils.isEmpty(publishtime)){
            monographsEntity.setPublishtime(publishtime);
        }
        if (!StringUtils.isEmpty(completetime)){
            monographsEntity.setCompletetime(completetime);
        }
        monographsEntity.setId(UUID.randomUUID().toString());
        monographsEntity.setStatus(0);
        template.save(monographsEntity);
    }

    public List<MonographsEntity> list(int start, int end, String monographsname) {
        try {
            String sql = "";
            List<MonographsEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(monographsname)) {
                sql = "select th.*,te.`name` AS teacherName from monographs th LEFT JOIN teacher te ON th.tid=te.id ORDER by status limit " + start + "," + end + "";
            } else {
                sql = "select th.*,te.`name` AS teacherName from monographs th LEFT JOIN teacher te ON th.tid=te.id WHERE th.monographsname like '%" + monographsname + "%' ORDER BY status  limit " + start + "," + end + "";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                MonographsEntity monographsEntity=new MonographsEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                monographsEntity=gson.fromJson((gson.toJson(rowData)),MonographsEntity.class);
                list.add(monographsEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCount(String monographsname) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(monographsname)) {
                sql = "select * from monographs";
            } else {
                sql = "select * from monographs WHERE monographsname like '%" + monographsname + "%'";
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

    public void update(String id, Integer status,String monographsname,String author,String publishtime,String completetime) {
        MonographsEntity monographsEntity = (MonographsEntity) template.get(MonographsEntity.class, id);
        if (null!=status){
            monographsEntity.setStatus(status);
        }else {
            monographsEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(monographsname)){
            monographsEntity.setMonographsname(monographsname);
        }
        if (!StringUtils.isEmpty(author)){
            monographsEntity.setAuthor(author);
        }
        if (!StringUtils.isEmpty(publishtime)){
            monographsEntity.setPublishtime(publishtime);
        }
        if (!StringUtils.isEmpty(completetime)){
            monographsEntity.setCompletetime(completetime);
        }
        template.update(monographsEntity);
    }

    /**
     * 删除
     */
    public void Delete(String id) {
        MonographsEntity monographsEntity=(MonographsEntity) template.load(MonographsEntity.class,id);
        template.delete(monographsEntity);
    }
}
