package com.medscape.android.reference.exception;

public class ContentVersionNotSupportedException extends Exception {
    public ContentVersionNotSupportedException(String str, Throwable th) {
        super(str, th);
    }

    public ContentVersionNotSupportedException(String str) {
        super(str);
    }
}
