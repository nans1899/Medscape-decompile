package com.tapstream.sdk.errors;

public class EventAlreadyFiredException extends ApiException {
    public EventAlreadyFiredException() {
    }

    public EventAlreadyFiredException(String str) {
        super(str);
    }

    public EventAlreadyFiredException(String str, Throwable th) {
        super(str, th);
    }

    public EventAlreadyFiredException(Throwable th) {
        super(th);
    }

    public EventAlreadyFiredException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}
