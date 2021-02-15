package com.wbmd.qxcalculator.model.db.v10;

public class DBItemFileZip {
    private Long id;
    private String identifier;
    private Long size;
    private String url;

    public DBItemFileZip() {
    }

    public DBItemFileZip(Long l) {
        this.id = l;
    }

    public DBItemFileZip(Long l, String str, String str2, Long l2) {
        this.id = l;
        this.identifier = str;
        this.url = str2;
        this.size = l2;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }
}
