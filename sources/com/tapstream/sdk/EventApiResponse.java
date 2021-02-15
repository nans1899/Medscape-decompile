package com.tapstream.sdk;

import com.tapstream.sdk.http.HttpResponse;

public class EventApiResponse implements ApiResponse {
    private final HttpResponse response;

    public EventApiResponse(HttpResponse httpResponse) {
        this.response = httpResponse;
    }

    public HttpResponse getHttpResponse() {
        return this.response;
    }
}
