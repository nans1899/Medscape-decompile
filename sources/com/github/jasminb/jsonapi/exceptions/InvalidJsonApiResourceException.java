package com.github.jasminb.jsonapi.exceptions;

public class InvalidJsonApiResourceException extends RuntimeException {
    public InvalidJsonApiResourceException() {
        super("Resource must contain at least one of 'data', 'error' or 'meta' nodes.");
    }
}
