package com.medscape.android.reference.exception;

public class ContentNotFoundException extends Exception {
    public ContentNotFoundException(String str, Throwable th) {
        super(str, th);
    }

    public ContentNotFoundException(String str) {
        super(str);
    }
}
