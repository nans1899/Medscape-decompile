package com.webmd.webmdrx.util;

public class WebMDException extends Throwable {
    private final String mMessage;

    public WebMDException(String str) {
        this.mMessage = str;
    }

    public String getMessage() {
        return this.mMessage;
    }
}
