package com.github.jasminb.jsonapi.exceptions;

import com.github.jasminb.jsonapi.models.errors.Errors;

public class ResourceParseException extends RuntimeException {
    private final Errors errors;

    public ResourceParseException(Errors errors2) {
        super(errors2.toString());
        this.errors = errors2;
    }

    public Errors getErrors() {
        return this.errors;
    }
}
