package com.wbmd.qxcalculator.model.db.v10;

public class DBDefinitionSection {
    private String body;
    private Long definitionId;
    private Long id;
    private String identifier;
    private Integer position;
    private String title;

    public DBDefinitionSection() {
    }

    public DBDefinitionSection(Long l) {
        this.id = l;
    }

    public DBDefinitionSection(Long l, String str, String str2, String str3, Integer num, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.body = str3;
        this.position = num;
        this.definitionId = l2;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public Long getDefinitionId() {
        return this.definitionId;
    }

    public void setDefinitionId(Long l) {
        this.definitionId = l;
    }
}
