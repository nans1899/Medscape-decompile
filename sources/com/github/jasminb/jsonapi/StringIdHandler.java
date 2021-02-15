package com.github.jasminb.jsonapi;

public class StringIdHandler implements ResourceIdHandler {
    public String fromString(String str) {
        return str;
    }

    public String asString(Object obj) {
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }
}
