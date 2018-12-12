package com.pl.model;

import javax.persistence.*;
import java.sql.Timestamp;

public class PatentEntity {
    private String id;
    private String patentid;
    private String author;
    private String patentname;
    private String  applicationtime;
    private String authorizeder;
    private String  noticeday;
    private Integer status;
    private String tid;
    private Integer index;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatentid() {
        return patentid;
    }

    public void setPatentid(String patentid) {
        this.patentid = patentid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPatentname() {
        return patentname;
    }

    public void setPatentname(String patentname) {
        this.patentname = patentname;
    }

    public String getApplicationtime() {
        return applicationtime;
    }

    public void setApplicationtime(String applicationtime) {
        this.applicationtime = applicationtime;
    }

    public String getAuthorizeder() {
        return authorizeder;
    }

    public void setAuthorizeder(String authorizeder) {
        this.authorizeder = authorizeder;
    }

    public String getNoticeday() {
        return noticeday;
    }

    public void setNoticeday(String noticeday) {
        this.noticeday = noticeday;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PatentEntity{" +
                "id='" + id + '\'' +
                ", patentid='" + patentid + '\'' +
                ", author='" + author + '\'' +
                ", patentname='" + patentname + '\'' +
                ", applicationtime='" + applicationtime + '\'' +
                ", authorizeder='" + authorizeder + '\'' +
                ", noticeday='" + noticeday + '\'' +
                ", status=" + status +
                ", tid='" + tid + '\'' +
                ", index=" + index +
                '}';
    }
}
