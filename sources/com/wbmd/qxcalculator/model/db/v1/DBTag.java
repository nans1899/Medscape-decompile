package com.wbmd.qxcalculator.model.db.v1;

public class DBTag {
    private Long contentItemId;
    private Long id;
    private String identifier;
    private String name;

    public DBTag() {
    }

    public DBTag(Long l) {
        this.id = l;
    }

    public DBTag(Long l, String str, String str2, Long l2) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.contentItemId = l2;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }
}
