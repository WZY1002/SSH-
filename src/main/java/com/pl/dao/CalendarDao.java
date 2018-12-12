package com.pl.dao;

import com.pl.model.CalendarEntiy;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface CalendarDao {
    void add(String title,String start,String end);
    void update(String start,String end,String title);
    void delete(String start,String end);
    public List<Map> list();
}
