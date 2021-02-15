package com.wbmd.qxcalculator.model.db.v6;

public class DBAdTargetingParameter {
    private Long adSettingId;
    private Long id;
    private String identifier;
    private String key;
    private String value;

    public DBAdTargetingParameter() {
    }

    public DBAdTargetingParameter(Long l) {
        this.id = l;
    }

    public DBAdTargetingParameter(Long l, String str, String str2, String str3, Long l2) {
        this.id = l;
        this.identifier = str;
        this.key = str2;
        this.value = str3;
        this.adSettingId = l2;
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public Long getAdSettingId() {
        return this.adSettingId;
    }

    public void setAdSettingId(Long l) {
        this.adSettingId = l;
    }
}
