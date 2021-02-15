package com.wbmd.qxcalculator.model.db.v4;

public class DBSpecialty {
    private String categoryIdentifier;
    private String contributingAuthor;
    private Long filterId;
    private Long id;
    private Long identifier;
    private String name;

    public DBSpecialty() {
    }

    public DBSpecialty(Long l) {
        this.id = l;
    }

    public DBSpecialty(Long l, Long l2, String str, String str2, String str3, Long l3) {
        this.id = l;
        this.identifier = l2;
        this.name = str;
        this.categoryIdentifier = str2;
        this.contributingAuthor = str3;
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

    public String getCategoryIdentifier() {
        return this.categoryIdentifier;
    }

    public void setCategoryIdentifier(String str) {
        this.categoryIdentifier = str;
    }

    public String getContributingAuthor() {
        return this.contributingAuthor;
    }

    public void setContributingAuthor(String str) {
        this.contributingAuthor = str;
    }

    public Long getFilterId() {
        return this.filterId;
    }

    public void setFilterId(Long l) {
        this.filterId = l;
    }
}
