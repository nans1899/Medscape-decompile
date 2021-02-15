package com.wbmd.qxcalculator.model.db.v10;

public class DBErrorCheck {
    private String answer;
    private Long calculatorId;
    private String formula;
    private Long id;
    private String identifier;
    private Long orderedId;
    private Integer position;
    private String title;
    private String type;

    public DBErrorCheck() {
    }

    public DBErrorCheck(Long l) {
        this.id = l;
    }

    public DBErrorCheck(Long l, String str, Long l2, Integer num, String str2, String str3, String str4, String str5, Long l3) {
        this.id = l;
        this.identifier = str;
        this.orderedId = l2;
        this.position = num;
        this.type = str2;
        this.title = str3;
        this.answer = str4;
        this.formula = str5;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String str) {
        this.formula = str;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }
}
