package com.wbmd.qxcalculator.model.db.v2;

public class DBLinkedCalculatorItem {
    private String calculatorIdentifier;
    private Long id;
    private String identifier;
    private Long questionId;
    private String resultConvertFormula;
    private String resultUnits;

    public DBLinkedCalculatorItem() {
    }

    public DBLinkedCalculatorItem(Long l) {
        this.id = l;
    }

    public DBLinkedCalculatorItem(Long l, String str, String str2, String str3, String str4, Long l2) {
        this.id = l;
        this.identifier = str;
        this.calculatorIdentifier = str2;
        this.resultConvertFormula = str3;
        this.resultUnits = str4;
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

    public String getCalculatorIdentifier() {
        return this.calculatorIdentifier;
    }

    public void setCalculatorIdentifier(String str) {
        this.calculatorIdentifier = str;
    }

    public String getResultConvertFormula() {
        return this.resultConvertFormula;
    }

    public void setResultConvertFormula(String str) {
        this.resultConvertFormula = str;
    }

    public String getResultUnits() {
        return this.resultUnits;
    }

    public void setResultUnits(String str) {
        this.resultUnits = str;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }
}
