package com.wbmd.qxcalculator.model.db.v8;

public class DBResult {
    private String answer;
    private String answerPrimary;
    private String answerSecondary;
    private Long calculatorId;
    private String conditionFormula;
    private String formula;
    private Long id;
    private String identifier;
    private Long orderedId;
    private Integer position;
    private String subTitle;
    private String subTitleFormula;
    private String title;
    private String titleFormula;
    private String type;

    public DBResult() {
    }

    public DBResult(Long l) {
        this.id = l;
    }

    public DBResult(Long l, String str, Long l2, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Long l3) {
        this.id = l;
        this.identifier = str;
        this.orderedId = l2;
        this.position = num;
        this.type = str2;
        this.conditionFormula = str3;
        this.title = str4;
        this.titleFormula = str5;
        this.subTitle = str6;
        this.subTitleFormula = str7;
        this.formula = str8;
        this.answer = str9;
        this.answerPrimary = str10;
        this.answerSecondary = str11;
        this.calculatorId = l3;
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

    public Long getOrderedId() {
        return this.orderedId;
    }

    public void setOrderedId(Long l) {
        this.orderedId = l;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getConditionFormula() {
        return this.conditionFormula;
    }

    public void setConditionFormula(String str) {
        this.conditionFormula = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitleFormula() {
        return this.titleFormula;
    }

    public void setTitleFormula(String str) {
        this.titleFormula = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getSubTitleFormula() {
        return this.subTitleFormula;
    }

    public void setSubTitleFormula(String str) {
        this.subTitleFormula = str;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String str) {
        this.formula = str;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public String getAnswerPrimary() {
        return this.answerPrimary;
    }

    public void setAnswerPrimary(String str) {
        this.answerPrimary = str;
    }

    public String getAnswerSecondary() {
        return this.answerSecondary;
    }

    public void setAnswerSecondary(String str) {
        this.answerSecondary = str;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }
}
