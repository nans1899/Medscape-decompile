package com.medscape.android.reference.model;

import java.io.Serializable;

public class ClinicalReference implements Serializable {
    private static final long serialVersionUID = 1;
    private int folderId;
    private boolean isBrowsable;
    private String name;
    private int parentId;

    public int getFolderId() {
        return this.folderId;
    }

    public void setFolderId(int i) {
        this.folderId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int i) {
        this.parentId = i;
    }

    public boolean isBrowsable() {
        return this.isBrowsable;
    }

    public void setBrowsable(boolean z) {
        this.isBrowsable = z;
    }
}
