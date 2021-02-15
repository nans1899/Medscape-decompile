package com.github.jasminb.jsonapi;

public interface ResourceIdHandler {
    String asString(Object obj);

    Object fromString(String str);
}
