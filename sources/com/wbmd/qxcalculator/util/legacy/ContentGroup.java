package com.wbmd.qxcalculator.util.legacy;

import java.util.ArrayList;
import java.util.List;

public class ContentGroup extends ContentNode {
    private boolean mExpanded;
    private Integer mIdentifier;
    private List<String> mRegions;
    private String mTitle;

    public Integer getIdentifier() {
        return this.mIdentifier;
    }

    public void setIdentifier(Integer num) {
        this.mIdentifier = num;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public void setExpanded(boolean z) {
        this.mExpanded = z;
    }

    public List<String> getRegions() {
        return this.mRegions;
    }

    public void addRegion(String str) {
        if (this.mRegions == null) {
            this.mRegions = new ArrayList();
        }
        this.mRegions.add(str);
    }

    public boolean isValidRegion(String str) {
        List<String> list;
        if (str == null || (list = this.mRegions) == null) {
            return true;
        }
        for (String equals : list) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
