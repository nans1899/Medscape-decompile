package com.tapstream.sdk.errors;

import com.dd.plist.ASCIIPropertyListParser;
import com.tapstream.sdk.http.HttpResponse;

public class UnrecoverableApiException extends ApiException {
    private final HttpResponse response;

    public UnrecoverableApiException(HttpResponse httpResponse) {
        this.response = httpResponse;
    }

    public UnrecoverableApiException(HttpResponse httpResponse, String str) {
        super(str);
        this.response = httpResponse;
    }

    public UnrecoverableApiException(HttpResponse httpResponse, String str, Throwable th) {
        super(str, th);
        this.response = httpResponse;
    }

    public UnrecoverableApiException(HttpResponse httpResponse, Throwable th) {
        super(th);
        this.response = httpResponse;
    }

    public UnrecoverableApiException(HttpResponse httpResponse, String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
        this.response = httpResponse;
    }

    public HttpResponse getHttpResponse() {
        return this.response;
    }

    public String toString() {
        return "UnrecoverableApiException{response=" + this.response + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
