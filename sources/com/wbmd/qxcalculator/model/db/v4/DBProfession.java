package com.wbmd.qxcalculator.model.db.v4;

public class DBProfession {
    private Long filterId;
    private Long id;
    private Long identifier;
    private String name;

    public DBProfession() {
    }

    public DBProfession(Long l) {
        this.id = l;
    }

    public DBProfession(Long l, Long l2, String str, Long l3) {
        this.id = l;
        this.identifier = l2;
        this.name = str;
        this.filterId = l3;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
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

    public Long getFilterId() {
        return this.filterId;
    }

    public void setFilterId(Long l) {
        this.filterId = l;
    }
}
