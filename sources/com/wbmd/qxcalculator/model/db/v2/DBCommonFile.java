package com.wbmd.qxcalculator.model.db.v2;

public class DBCommonFile {
    private Long id;
    private String identifier;

    public DBCommonFile() {
    }

    public DBCommonFile(Long l) {
        this.id = l;
    }

    public DBCommonFile(Long l, String str) {
        this.id = l;
        this.identifier = str;
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
}
