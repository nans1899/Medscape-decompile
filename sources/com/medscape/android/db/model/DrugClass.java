package com.medscape.android.db.model;

import java.io.Serializable;

public class DrugClass implements Serializable {
    private static final long serialVersionUID = 1043819661813723070L;
    private int classId;
    private String className;
    private int id;
    private int parentId;
    private int singleLevel;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int i) {
        this.classId = i;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setParentId(int i) {
        this.parentId = i;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setSingleLevel(int i) {
        this.singleLevel = i;
    }

    public int getSingleLevel() {
        return this.singleLevel;
    }
}
