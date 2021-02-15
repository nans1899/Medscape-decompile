package com.wbmd.qxcalculator.model.db.v8;

public class DBConsentUser {
    private Long id;
    private String identifier;
    private Boolean isExplicit;
    private Boolean isOptIn;
    private String label;
    private Long userId;

    public DBConsentUser() {
    }

    public DBConsentUser(Long l) {
        this.id = l;
    }

    public DBConsentUser(Long l, String str, String str2, Boolean bool, Boolean bool2, Long l2) {
        this.id = l;
        this.identifier = str;
        this.label = str2;
        this.isOptIn = bool;
        this.isExplicit = bool2;
        this.userId = l2;
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

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public Boolean getIsOptIn() {
        return this.isOptIn;
    }

    public void setIsOptIn(Boolean bool) {
        this.isOptIn = bool;
    }

    public Boolean getIsExplicit() {
        return this.isExplicit;
    }

    public void setIsExplicit(Boolean bool) {
        this.isExplicit = bool;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long l) {
        this.userId = l;
    }
}
