package com.wbmd.qxcalculator.model.db.v7;

public class DBAnswerChoice {
    private Double answerFactor;
    private String errorMessage;
    private Long id;
    private String identifier;
    private Long questionId;
    private String successMessage;
    private String titlePrimary;
    private String titleSecondary;
    private Boolean unitIndependent;
    private String warningMessage;

    public DBAnswerChoice() {
    }

    public DBAnswerChoice(Long l) {
        this.id = l;
    }

    public DBAnswerChoice(Long l, String str, String str2, String str3, Double d, Boolean bool, String str4, String str5, String str6, Long l2) {
        this.id = l;
        this.identifier = str;
        this.titlePrimary = str2;
        this.titleSecondary = str3;
        this.answerFactor = d;
        this.unitIndependent = bool;
        this.successMessage = str4;
        this.warningMessage = str5;
        this.errorMessage = str6;
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

    public String getSuccessMessage() {
        return this.successMessage;
    }

    public void setSuccessMessage(String str) {
        this.successMessage = str;
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }

    public void setWarningMessage(String str) {
        this.warningMessage = str;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }
}
