package com.github.jasminb.jsonapi;

public class LongIdHandler implements ResourceIdHandler {
    public String asString(Object obj) {
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public Long fromString(String str) {
        if (str != null) {
            return Long.valueOf(str);
        }
        return null;
    }
}
