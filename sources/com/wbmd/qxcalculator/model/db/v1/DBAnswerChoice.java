package com.wbmd.qxcalculator.model.db.v1;

public class DBAnswerChoice {
    private Double answerFactor;
    private Long id;
    private String identifier;
    private Long questionId;
    private String titlePrimary;
    private String titleSecondary;
    private Boolean unitIndependent;

    public DBAnswerChoice() {
    }

    public DBAnswerChoice(Long l) {
        this.id = l;
    }

    public DBAnswerChoice(Long l, String str, String str2, String str3, Double d, Boolean bool, Long l2) {
        this.id = l;
        this.identifier = str;
        this.titlePrimary = str2;
        this.titleSecondary = str3;
        this.answerFactor = d;
        this.unitIndependent = bool;
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

    public String getTitlePrimary() {
        return this.titlePrimary;
    }

    public void setTitlePrimary(String str) {
        this.titlePrimary = str;
    }

    public String getTitleSecondary() {
        return this.titleSecondary;
    }

    public void setTitleSecondary(String str) {
        this.titleSecondary = str;
    }

    public Double getAnswerFactor() {
        return this.answerFactor;
    }

    public void setAnswerFactor(Double d) {
        this.answerFactor = d;
    }

    public Boolean getUnitIndependent() {
        return this.unitIndependent;
    }

    public void setUnitIndependent(Boolean bool) {
        this.unitIndependent = bool;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }
}
