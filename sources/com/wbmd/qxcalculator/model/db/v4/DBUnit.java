package com.wbmd.qxcalculator.model.db.v4;

public class DBUnit {
    private Long id;
    private String identifier;
    private Double initialValue;
    private Double maxValue;
    private String maxValueMessage;
    private Double minValue;
    private String minValueMessage;
    private Long questionId;
    private String title;
    private String type;
    private Double unitFactor;

    public DBUnit() {
    }

    public DBUnit(Long l) {
        this.id = l;
    }

    public DBUnit(Long l, String str, String str2, String str3, Double d, Double d2, Double d3, Double d4, String str4, String str5, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.type = str3;
        this.initialValue = d;
        this.unitFactor = d2;
        this.minValue = d3;
        this.maxValue = d4;
        this.minValueMessage = str4;
        this.maxValueMessage = str5;
        this.questionId = l2;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Double getInitialValue() {
        return this.initialValue;
    }

    public void setInitialValue(Double d) {
        this.initialValue = d;
    }

    public Double getUnitFactor() {
        return this.unitFactor;
    }

    public void setUnitFactor(Double d) {
        this.unitFactor = d;
    }

    public Double getMinValue() {
        return this.minValue;
    }

    public void setMinValue(Double d) {
        this.minValue = d;
    }

    public Double getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(Double d) {
        this.maxValue = d;
    }

    public String getMinValueMessage() {
        return this.minValueMessage;
    }

    public void setMinValueMessage(String str) {
        this.minValueMessage = str;
    }

    public String getMaxValueMessage() {
        return this.maxValueMessage;
    }

    public void setMaxValueMessage(String str) {
        this.maxValueMessage = str;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }
}
