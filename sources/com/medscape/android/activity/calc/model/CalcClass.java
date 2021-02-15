package com.medscape.android.activity.calc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CalcClass implements Serializable {
    private ArrayList folderContents;
    private int folderId;
    private boolean isBrowsable;
    private boolean isFolderList;
    private String segmentName;

    public int getFolderId() {
        return this.folderId;
    }

    public void setFolderId(int i) {
        this.folderId = i;
    }

    public boolean isFolderList() {
        return this.isFolderList;
    }

    public void setFolderList(boolean z) {
        this.isFolderList = z;
    }

    public ArrayList getFolderContents() {
        return this.folderContents;
    }

    public void setFolderContents(ArrayList arrayList) {
        this.folderContents = arrayList;
    }

    public void setSegmentName(String str) {
        this.segmentName = str;
    }

    public String getSegmentName() {
        return this.segmentName;
    }

    public void setBrowsable(boolean z) {
        this.isBrowsable = z;
    }

    public boolean isBrowsable() {
        return this.isBrowsable;
    }
}
