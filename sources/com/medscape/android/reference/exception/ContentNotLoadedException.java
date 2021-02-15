package com.medscape.android.reference.exception;

public class ContentNotLoadedException extends Exception {
    public ContentNotLoadedException(String str, Throwable th) {
        super(str, th);
    }

    public ContentNotLoadedException(String str) {
        super(str);
    }
}
