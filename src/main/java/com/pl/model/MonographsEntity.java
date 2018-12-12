package com.pl.model;

import javax.persistence.*;
import java.sql.Timestamp;

public class MonographsEntity {
    private String id;
    private String monographsname;
    private String author;
    private String publishtime;
    private String completetime;
    private Integer status;
    private Integer index;
    private String tid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonographsname() {
        return monographsname;
    }

    public void setMonographsname(String monographsname) {
        this.monographsname = monographsname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MonographsEntity{" +
                "id='" + id + '\'' +
                ", monographsname='" + monographsname + '\'' +
                ", author='" + author + '\'' +
                ", publishtime=" + publishtime +
                ", completetime=" + completetime +
                ", status=" + status +
                '}';
    }
}
