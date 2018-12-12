package com.pl.model;

import javax.persistence.*;

@Entity
@Table(name = "class", schema = "com/pl", catalog = "")
public class ClassEntity {
    private String id;
    private String classname;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "classname")
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassEntity that = (ClassEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (classname != null ? !classname.equals(that.classname) : that.classname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (classname != null ? classname.hashCode() : 0);
        return result;
    }
}
