package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.CopyrightDao;
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
public class copyrightDaoImpl implements CopyrightDao {

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

    public List<CopyrightEntity> list(int start, int end, String workname) {
        try {
            String sql = "";
            List<CopyrightEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(workname)) {
                sql = "select th.*,te.`name` AS teacherName from copyright th LEFT JOIN teacher te ON th.tid=te.id ORDER by status limit " + start + "," + end + "";
            } else {
                sql = "select th.*,te.`name` AS teacherName from copyright th LEFT JOIN teacher te ON th.tid=te.id WHERE th.workname like '%" + workname + "%' ORDER BY status  limit " + start + "," + end + "";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                CopyrightEntity copyrightEntity=new CopyrightEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                copyrightEntity=gson.fromJson((gson.toJson(rowData)),CopyrightEntity.class);
                list.add(copyrightEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCount(String workname) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(workname)) {
                sql = "select * from copyright";
            } else {
                sql = "select * from copyright WHERE workname like '%" + workname + "%'";
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

    public List<CopyrightEntity> listByTeacher(int start, int end, String id, String workname) {
        try {
            String sql = "";
            List<CopyrightEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(workname)) {
                sql = "select * from copyright  WHERE 1=1 AND tid='"+id+"' ORDER  BY status limit " + start + "," + end + " ";
            } else {
                sql = "select * from copyright WHERE tid='"+id+"' AND copyright.workname like '%" + workname + "%' ORDER  BY status limit " + start + "," + end + "  ";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                CopyrightEntity copyrightEntity=new CopyrightEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                copyrightEntity=gson.fromJson((gson.toJson(rowData)),CopyrightEntity.class);
                list.add(copyrightEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCountByTeacher(String id, String workname) {
        int total=0;
        try {
            String sql = "";
            Connection conn = getConn();
            if (StringUtils.isEmpty(workname)) {
                sql = "select * from copyright  WHERE 1=1 AND tid='"+id+"' ORDER  BY status ";
            } else {
                sql = "select * from copyright WHERE tid='"+id+"' AND copyright.workname like '%" + workname + "%' ORDER  BY status ";
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

    public void add(String tid, String workname, String author, String worktype, String copyrighter, String completetime, String publishtime, String registration, String registid, String access) {
        CopyrightEntity copyrightEntity=new CopyrightEntity();
        if (!StringUtils.isEmpty(tid)){
            copyrightEntity.setTid(tid);
        }
        if (!StringUtils.isEmpty(workname)){
            copyrightEntity.setWorkname(workname);
        }
        if (!StringUtils.isEmpty(author)){
            copyrightEntity.setAuthor(author);
        }
        if (!StringUtils.isEmpty(worktype)){
            copyrightEntity.setWorktype(worktype);
        }
        if (!StringUtils.isEmpty(completetime)){
            copyrightEntity.setCompletetime(completetime);
        }
        if (!StringUtils.isEmpty(copyrighter)){
            copyrightEntity.setCopyrighter(copyrighter);
        }
        if (!StringUtils.isEmpty(publishtime)){
            copyrightEntity.setPublishtime(publishtime);
        }
        if (!StringUtils.isEmpty(registration)){
            copyrightEntity.setRegistration(registration);
        }
        if (!StringUtils.isEmpty(registid)){
            copyrightEntity.setRegistid(registid);
        }
        if (!StringUtils.isEmpty(access)){
            copyrightEntity.setAccess(access);
        }
        copyrightEntity.setId(UUID.randomUUID().toString());
        copyrightEntity.setStatus(0);
        template.save(copyrightEntity);
    }

    public void update(String id, Integer status,String workname,String author,String worktype,String copyrighter,String completetime,String publishtime,String registration,String registid,String access) {
        CopyrightEntity copyrightEntity = (CopyrightEntity) template.get(CopyrightEntity.class, id);
        if (null!=status){
            copyrightEntity.setStatus(status);
        }else{
            copyrightEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(workname)){
            copyrightEntity.setWorkname(workname);
        }
        if (!StringUtils.isEmpty(author)){
            copyrightEntity.setAuthor(author);
        }
        if (!StringUtils.isEmpty(worktype)){
            copyrightEntity.setWorktype(worktype);
        }
        if (!StringUtils.isEmpty(copyrighter)){
            copyrightEntity.setCopyrighter(copyrighter);
        }
        if (!StringUtils.isEmpty(completetime)){
            copyrightEntity.setCompletetime(completetime);
        }
        if (!StringUtils.isEmpty(publishtime)){
            copyrightEntity.setPublishtime(publishtime);
        }
        if (!StringUtils.isEmpty(registration)){
            copyrightEntity.setRegistration(registration);
        }
        if (!StringUtils.isEmpty(registid)){
            copyrightEntity.setRegistid(registid);
        }
        if (!StringUtils.isEmpty(access)){
            copyrightEntity.setAccess(access);
        }
        template.update(copyrightEntity);
    }

    /**
     * 删除
     */
    public void Delete(String id) {
        CopyrightEntity copyrightEntity=(CopyrightEntity) template.load(CopyrightEntity.class,id);
        template.delete(copyrightEntity);
    }
}
