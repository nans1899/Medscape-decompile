package com.medscape.android.parser.model;

public class Page {
    private String pageArticleId;
    private String pageClass;
    private String pageLeadConceptId;
    private String pageLeadSpecialtyId;
    private String pagecontentGroup;

    public Page(String str, String str2, String str3, String str4, String str5) {
        this.pageClass = str;
        this.pageArticleId = str2;
        this.pagecontentGroup = str3;
        this.pageLeadConceptId = str4;
        this.pageLeadSpecialtyId = str5;
    }

    public Page() {
    }

    public String getPageClass() {
        return this.pageClass;
    }

    public void setPageClass(String str) {
        this.pageClass = str;
    }

    public String getPageArticleId() {
        return this.pageArticleId;
    }

    public void setPageArticleId(String str) {
        this.pageArticleId = str;
    }

    public String getPagecontentGroup() {
        return this.pagecontentGroup;
    }

    public void setPagecontentGroup(String str) {
        this.pagecontentGroup = str;
    }

    public String getPageLeadConceptId() {
        return this.pageLeadConceptId;
    }

    public void setPageLeadConceptId(String str) {
        this.pageLeadConceptId = str;
    }

    public String getPageLeadSpecialtyId() {
        return this.pageLeadSpecialtyId;
    }

    public void setPageLeadSpecialtyId(String str) {
        this.pageLeadSpecialtyId = str;
    }
}
