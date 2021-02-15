package com.medscape.android.drugs.model;

import com.medscape.android.contentviewer.CrossLink;
import java.io.Serializable;
import java.util.List;

public class DrugMonographSection implements Serializable {
    private static final long serialVersionUID = 3214203504642687810L;
    private int index;
    private List<String> listItems;
    private List<subSection> listItems2;
    private List<String> subSectionTitleList;
    private List<DrugMonographSection> subsections;
    private String title;

    public static class subSection implements Serializable {
        public String crossLinkId;
        public CrossLink.Type crossLinkType;
        public int index;
        public String item;
        public String title;
    }

    public List<String> getListItems() {
        return this.listItems;
    }

    public void setListItems(List<String> list) {
        this.listItems = list;
    }

    public List<DrugMonographSection> getSubsections() {
        return this.subsections;
    }

    public void setSubsections(List<DrugMonographSection> list) {
        this.subsections = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setSubSectionTitle(List<String> list) {
        this.subSectionTitleList = list;
    }

    public List<String> getSubSectionTitle() {
        return this.subSectionTitleList;
    }

    public void setListItems2(List<subSection> list) {
        this.listItems2 = list;
    }

    public List<subSection> getListItems2() {
        return this.listItems2;
    }
}
