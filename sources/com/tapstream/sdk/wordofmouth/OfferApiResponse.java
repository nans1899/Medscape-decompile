package com.tapstream.sdk.wordofmouth;

import com.tapstream.sdk.ApiResponse;
import com.tapstream.sdk.http.HttpResponse;

public class OfferApiResponse implements ApiResponse {
    private Offer offer;
    private HttpResponse response;

    public OfferApiResponse(HttpResponse httpResponse, Offer offer2) {
        this.response = httpResponse;
        this.offer = offer2;
    }

    public Offer getOffer() {
        return this.offer;
    }

    public HttpResponse getHttpResponse() {
        return this.response;
    }
}
