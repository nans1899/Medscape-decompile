package com.tapstream.sdk.errors;

import com.dd.plist.ASCIIPropertyListParser;
import com.tapstream.sdk.http.HttpResponse;

public class RecoverableApiException extends ApiException {
    private final HttpResponse response;

    public RecoverableApiException(HttpResponse httpResponse) {
        this.response = httpResponse;
    }

    public RecoverableApiException(HttpResponse httpResponse, String str) {
        super(str);
        this.response = httpResponse;
    }

    public RecoverableApiException(HttpResponse httpResponse, String str, Throwable th) {
        super(str, th);
        this.response = httpResponse;
    }

    public RecoverableApiException(HttpResponse httpResponse, Throwable th) {
        super(th);
        this.response = httpResponse;
    }

    public RecoverableApiException(HttpResponse httpResponse, String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
        this.response = httpResponse;
    }

    public HttpResponse getHttpResponse() {
        return this.response;
    }

    public String toString() {
        return "RecoverableApiException{response=" + this.response + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
