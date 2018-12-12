package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.ThesisDao;
import com.pl.model.TeacherEntity;
import com.pl.model.ThesisEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import sun.plugin.util.UIUtil;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@Repository
public class ThesisDaoImpl implements ThesisDao {
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

    public List<ThesisEntity> list(int start, int end, String title) {
        try {
            String sql = "";
            List<ThesisEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(title)) {
                sql = "select th.*,te.`name`AS teacherName from thesis th LEFT JOIN teacher te ON th.tid=te.id ORDER by status limit " + start + "," + end + "";
            } else {
                sql = "select th.*,te.`name`AS teacherName from thesis th LEFT JOIN teacher te ON th.tid=te.id WHERE th.title like '%" + title + "%' ORDER BY status  limit " + start + "," + end + "";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
               ThesisEntity thesisEntity=new ThesisEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                thesisEntity=gson.fromJson((gson.toJson(rowData)),ThesisEntity.class);
                list.add(thesisEntity);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCount(String title) {
        int total=0;
        try {
            String sql = "";
            List<ThesisEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(title)) {
                sql = "select * from thesis";
            } else {
                sql = "select * from thesis WHERE thesis.title like '%" + title + "%'";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                total=total+1;
            }
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }


    public List<ThesisEntity> listByTeacher(int start, int end, String id, String title) {
        try {
            String sql = "";
            List<ThesisEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(title)) {
                sql = "select * from thesis  WHERE 1=1 AND tid='"+id+"' ORDER  BY status limit " + start + "," + end + " ";
            } else {
                sql = "select * from thesis WHERE tid='"+id+"' AND title like '%" + title + "%' ORDER  BY status limit " + start + "," + end + "  ";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                ThesisEntity thesisEntity=new ThesisEntity();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                thesisEntity=gson.fromJson((gson.toJson(rowData)),ThesisEntity.class);
                list.add(thesisEntity);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int ListCountByTeacher(String id, String title) {
        int total=0;
        try {
            String sql = "";
            List<ThesisEntity> list = new ArrayList();
            Connection conn = getConn();
            if (StringUtils.isEmpty(title)) {
                sql = "select * from thesis  WHERE 1=1 AND tid='"+id+"' ORDER  BY status ";
            } else {
                sql = "select * from thesis WHERE tid='"+id+"' AND title like '%" + title + "%' ORDER  BY status";
            }
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                total=total+1;
            }
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 审批论文
     */
    public void update(String id, Integer status,String title,String published_time,String magazine_name,String authors) {
        ThesisEntity thesisEntity = (ThesisEntity)template.get(ThesisEntity.class, id);
        if (null!=status){
            thesisEntity.setStatus(status);
        }
        if (!StringUtils.isEmpty(title)){
            thesisEntity.setTitle(title);
            thesisEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(published_time)){
            thesisEntity.setPublished_time(published_time);
            thesisEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(magazine_name)){
            thesisEntity.setMagazine_name(magazine_name);
            thesisEntity.setStatus(0);
        }
        if (!StringUtils.isEmpty(authors)){
            thesisEntity.setAuthors(authors);
            thesisEntity.setStatus(0);
        }
        template.update(thesisEntity);
    }

    /**
     * 删除论文
     */
    public void Delete(String id) {
        ThesisEntity thesisEntity=(ThesisEntity) template.load(ThesisEntity.class,id);
        template.delete(thesisEntity);
    }

    /**
    *新增论文记录
     */
    public void add(String id, String title, String published_time, String magazine_name, String authors) {
//      ThesisEntity thesisEntity = (ThesisEntity)template.get(ThesisEntity.class, id);
        ThesisEntity thesisEntity=new ThesisEntity();
        if (!StringUtils.isEmpty(id)){
            thesisEntity.setTid(id);
        }
        if (!StringUtils.isEmpty(title)){
            thesisEntity.setTitle(title);
        }
        if (!StringUtils.isEmpty(published_time)){
            thesisEntity.setPublished_time(published_time);
        }
        if (!StringUtils.isEmpty(magazine_name)){
            thesisEntity.setMagazine_name(magazine_name);
        }
        if (!StringUtils.isEmpty(authors)){
            thesisEntity.setAuthors(authors);
        }
        thesisEntity.setId(UUID.randomUUID().toString());
        thesisEntity.setStatus(0);
        template.save(thesisEntity);
    }
}
