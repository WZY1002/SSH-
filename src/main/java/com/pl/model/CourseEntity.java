package com.pl.model;

import javax.persistence.*;

@Entity
@Table(name = "course", schema = "com/pl", catalog = "")
public class CourseEntity {
    private String id;
    private String coursename;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coursename")
    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (coursename != null ? !coursename.equals(that.coursename) : that.coursename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (coursename != null ? coursename.hashCode() : 0);
        return result;
    }
}
