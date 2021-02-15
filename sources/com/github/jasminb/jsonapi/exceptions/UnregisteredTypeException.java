package com.github.jasminb.jsonapi.exceptions;

public class UnregisteredTypeException extends RuntimeException {
    private final String type;

    public UnregisteredTypeException(String str) {
        super("No class was registered for type '" + str + "'.");
        this.type = str;
    }

    public String getType() {
        return this.type;
    }
}
