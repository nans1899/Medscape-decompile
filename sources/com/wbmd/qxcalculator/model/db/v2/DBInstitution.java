package com.wbmd.qxcalculator.model.db.v2;

public class DBInstitution {
    private String country;
    private Long id;
    private Long identifier;
    private String name;

    public DBInstitution() {
    }

    public DBInstitution(Long l) {
        this.id = l;
    }

    public DBInstitution(Long l, String str, Long l2, String str2) {
        this.id = l;
        this.country = str;
        this.identifier = l2;
        this.name = str2;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public Long getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(Long l) {
        this.identifier = l;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
