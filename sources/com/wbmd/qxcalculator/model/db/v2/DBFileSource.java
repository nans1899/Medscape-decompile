package com.wbmd.qxcalculator.model.db.v2;

public class DBFileSource {
    private Long id;
    private String identifier;
    private String source;
    private String type;

    public DBFileSource() {
    }

    public DBFileSource(Long l) {
        this.id = l;
    }

    public DBFileSource(Long l, String str, String str2, String str3) {
        this.id = l;
        this.identifier = str;
        this.source = str2;
        this.type = str3;
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

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
