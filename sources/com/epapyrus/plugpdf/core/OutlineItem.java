package com.epapyrus.plugpdf.core;

import java.io.UnsupportedEncodingException;

public class OutlineItem {
    private int mDeps;
    private int mObjectID;
    private int mPageIdx;
    private int mParentObjID;
    private String mTitle;

    public OutlineItem(int i, String str, int i2) {
        this.mDeps = i;
        this.mTitle = str;
        this.mPageIdx = i2;
    }

    public int getDeps() {
        return this.mDeps;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getPageIdx() {
        return this.mPageIdx;
    }

    public int getObjectID() {
        return this.mObjectID;
    }

    public void setObjectID(int i) {
        this.mObjectID = i;
    }

    public int getParentObjID() {
        return this.mParentObjID;
    }

    public void setParentObjID(int i) {
        this.mParentObjID = i;
    }

    public byte[] getTitleBytes() {
        try {
            return this.mTitle.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
