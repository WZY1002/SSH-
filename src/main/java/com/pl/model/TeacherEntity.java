package com.pl.model;

import javax.persistence.*;
import java.sql.Timestamp;

public class TeacherEntity {
    private Integer index;
    private String name;
    private String cardId;
    private String sex;
    private String photo;
    private String nation;
    private String birth;
    private String place;
    private String politics;
    private String healthy;
    private String address;
    private String mobile_phone;
    private String home_phone;
    private Timestamp workingTime;
    private String identity;
    private String employ_time;
    private String education;
    private String school;
    private String department;
    private String id;
    private String password;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Timestamp workingTime) {
        this.workingTime = workingTime;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmploy_time() {
        return employ_time;
    }

    public void setEmploy_time(String employ_time) {
        this.employ_time = employ_time;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", sex='" + sex + '\'' +
                ", photo='" + photo + '\'' +
                ", nation='" + nation + '\'' +
                ", birth=" + birth +
                ", place='" + place + '\'' +
                ", politics='" + politics + '\'' +
                ", healthy='" + healthy + '\'' +
                ", address='" + address + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", home_phone='" + home_phone + '\'' +
                ", workingTime=" + workingTime +
                ", identity='" + identity + '\'' +
                ", employ_time=" + employ_time +
                ", education='" + education + '\'' +
                ", school='" + school + '\'' +
                ", department='" + department + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
