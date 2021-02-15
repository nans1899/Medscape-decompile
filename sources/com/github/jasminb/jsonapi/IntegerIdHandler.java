package com.github.jasminb.jsonapi;

public class IntegerIdHandler implements ResourceIdHandler {
    public String asString(Object obj) {
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public Integer fromString(String str) {
        if (str != null) {
            return Integer.valueOf(str);
        }
        return null;
    }
}
