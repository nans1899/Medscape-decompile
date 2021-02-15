package com.appboy.models.response;

public class ResponseError {
    private final String a;

    public ResponseError(String str) {
        this.a = str;
    }

    public String getMessage() {
        return this.a;
    }
}
