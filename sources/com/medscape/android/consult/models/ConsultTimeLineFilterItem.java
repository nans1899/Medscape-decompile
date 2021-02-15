package com.medscape.android.consult.models;

import java.util.ArrayList;
import java.util.List;

public class ConsultTimeLineFilterItem {
    private List<ConsultTimeLineFilterItem> mChildFilters = new ArrayList();
    private String mFilterType = "";
    private int mItemType;
    private String mTag;
    private String mTitle = "";

    public List<ConsultTimeLineFilterItem> getChildFilters() {
        return this.mChildFilters;
    }

    public void setChildFilters(List<ConsultTimeLineFilterItem> list) {
        this.mChildFilters = list;
    }

    public int getItemType() {
        return this.mItemType;
    }

    public void setItemType(int i) {
        this.mItemType = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getFilterType() {
        return this.mFilterType;
    }

    public void setFilterType(String str) {
        this.mFilterType = str;
    }
}
