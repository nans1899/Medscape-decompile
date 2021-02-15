package com.tapstream.sdk.http;

import com.dd.plist.ASCIIPropertyListParser;
import com.tapstream.sdk.errors.ApiException;
import com.tapstream.sdk.errors.RecoverableApiException;
import com.tapstream.sdk.errors.UnrecoverableApiException;
import java.io.UnsupportedEncodingException;

public class HttpResponse {
    public final byte[] body;
    public final String message;
    public final int status;

    public HttpResponse(int i, String str) {
        this(i, str, new byte[0]);
    }

    public HttpResponse(int i, String str, byte[] bArr) {
        this.status = i;
        this.message = str;
        this.body = bArr;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getBodyAsString() {
        if (this.body == null) {
            return null;
        }
        try {
            return new String(this.body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean succeeded() {
        int i = this.status;
        return i >= 200 && i < 300;
    }

    public boolean failed() {
        return !succeeded();
    }

    public boolean shouldRetry() {
        int i = this.status;
        return i >= 500 && i < 600;
    }

    public void throwOnError() throws ApiException {
        if (!succeeded()) {
            if (shouldRetry()) {
                throw new RecoverableApiException(this);
            }
            throw new UnrecoverableApiException(this, "HTTP Error, not recoverable.");
        }
    }

    public String toString() {
        return "HttpResponse{status=" + this.status + ", message='" + this.message + '\'' + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
