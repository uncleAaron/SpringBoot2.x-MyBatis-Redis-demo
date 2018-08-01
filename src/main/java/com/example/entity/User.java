package com.example.entity;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private sClass aSClass;

    public sClass getaSClass() {
        return aSClass;
    }

    public void setaSClass(sClass aSClass) {
        this.aSClass = aSClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aClass=" + aSClass +
                '}';
    }
}
