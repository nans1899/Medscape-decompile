package com.tapstream.sdk.errors;

public class ApiException extends Exception {
    public ApiException() {
    }

    public ApiException(String str) {
        super(str);
    }

    public ApiException(String str, Throwable th) {
        super(str, th);
    }

    public ApiException(Throwable th) {
        super(th);
    }

    public ApiException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}
