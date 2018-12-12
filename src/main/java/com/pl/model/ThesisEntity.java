package com.pl.model;

import javax.persistence.*;
import java.sql.Timestamp;

public class ThesisEntity {
    private String title;
    private String id;
    private String published_time;
    private String magazine_name;
    private String authors;
    private String tid;
    private Integer status;
    private Integer index;
    private String teacherName;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getMagazine_name() {
        return magazine_name;
    }

    public void setMagazine_name(String magazine_name) {
        this.magazine_name = magazine_name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ThesisEntity{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", published_time=" + published_time +
                ", magazine_name='" + magazine_name + '\'' +
                ", authors='" + authors + '\'' +
                ", tid='" + tid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
