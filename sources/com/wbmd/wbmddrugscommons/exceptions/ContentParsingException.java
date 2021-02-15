package com.wbmd.wbmddrugscommons.exceptions;

public class ContentParsingException extends Exception {
    public ContentParsingException(String str, Throwable th) {
        super(str, th);
    }

    public ContentParsingException(String str) {
        super(str);
    }
}
