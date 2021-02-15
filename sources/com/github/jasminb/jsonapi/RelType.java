package com.github.jasminb.jsonapi;

public enum RelType {
    SELF(JSONAPISpecConstants.SELF),
    RELATED(JSONAPISpecConstants.RELATED);
    
    private String relName;

    private RelType(String str) {
        this.relName = str;
    }

    public String getRelName() {
        return this.relName;
    }
}
