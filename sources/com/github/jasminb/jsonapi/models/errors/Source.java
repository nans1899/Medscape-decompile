package com.github.jasminb.jsonapi.models.errors;

public class Source {
    private String parameter;
    private String pointer;

    public String getPointer() {
        return this.pointer;
    }

    public void setPointer(String str) {
        this.pointer = str;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String str) {
        this.parameter = str;
    }
}
