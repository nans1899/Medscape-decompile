package com.epapyrus.plugpdf.core;

import android.graphics.RectF;

public class SearchInfo {
    private RectF[] mAreaList;
    private String mFilePath;
    private String mKeyword;
    private int mPageIdx;

    public SearchInfo() {
        reset();
    }

    public SearchInfo(String str, int i, RectF[] rectFArr, String str2) {
        set(str, i, rectFArr, str2);
    }

    public String getKeyword() {
        return this.mKeyword;
    }

    public int getPageIdx() {
        return this.mPageIdx;
    }

    public RectF[] getAreaList() {
        return this.mAreaList;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public void set(String str, int i, RectF[] rectFArr, String str2) {
        this.mKeyword = str;
        this.mPageIdx = i;
        this.mAreaList = rectFArr;
        this.mFilePath = str2;
    }

    public void reset() {
        this.mKeyword = null;
        this.mPageIdx = -1;
        this.mAreaList = null;
        this.mFilePath = null;
    }

    public boolean isEmpty() {
        return this.mKeyword == null;
    }
}
