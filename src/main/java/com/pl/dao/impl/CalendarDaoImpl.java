package com.pl.dao.impl;

import com.google.gson.Gson;
import com.pl.dao.CalendarDao;
import com.pl.model.CalendarEntiy;

import java.sql.*;
import java.util.*;

import com.pl.model.TeacherEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CalendarDaoImpl implements CalendarDao {
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

    /**
     * 批量日历事件
     */
    public List<Map> list() {
        try {
            List<Map> list = new ArrayList();
            String sql = "select * from calendar";
            Connection conn = getConn();
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();
                CalendarEntiy calendarEntiy = new CalendarEntiy();
                Map dataMap=new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                calendarEntiy = gson.fromJson((gson.toJson(rowData)), CalendarEntiy.class);
                dataMap.put("id",calendarEntiy.getId());
                dataMap.put("title","'"+calendarEntiy.getTitle()+"'");
                dataMap.put("start","'"+calendarEntiy.getStart()+"'");
                dataMap.put("end","'"+calendarEntiy.getEnd()+"'");
                list.add(dataMap);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(String title, String start, String end) {
        CalendarEntiy calendarentiy = new CalendarEntiy();
        if (!StringUtils.isEmpty(title)){
            calendarentiy.setTitle(title);
        }
        if (!StringUtils.isEmpty(start)){
            calendarentiy.setStart(start);
        }
        if (!StringUtils.isEmpty(end)){
            calendarentiy.setEnd(end);
        }
        calendarentiy.setId(UUID.randomUUID().toString());
        template.save(calendarentiy);
    }

    public void update(String start, String end, String title) {
        Connection conn = getConn();
        PreparedStatement pstmt;
        String sql="UPDATE calendar SET title='"+title+"' WHERE `start`="+start+" AND `end`="+end+"";
        try {
            pstmt = conn.prepareStatement(sql);
            int up=pstmt.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(String start, String end) {
        Connection conn = getConn();
        PreparedStatement pstmt;
        String sql="DELETE FROM calendar WHERE `start`="+start+" AND `end`="+end+"";
        try {
            pstmt = conn.prepareStatement(sql);
            int up=pstmt.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
