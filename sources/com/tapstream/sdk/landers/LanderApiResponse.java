package com.tapstream.sdk.landers;

import com.tapstream.sdk.ApiResponse;
import com.tapstream.sdk.http.HttpResponse;

public class LanderApiResponse implements ApiResponse {
    private Lander lander;
    private HttpResponse response;

    public LanderApiResponse(HttpResponse httpResponse, Lander lander2) {
        this.response = httpResponse;
        this.lander = lander2;
    }

    public Lander getLander() {
        return this.lander;
    }

    public HttpResponse getHttpResponse() {
        return this.response;
    }
}
