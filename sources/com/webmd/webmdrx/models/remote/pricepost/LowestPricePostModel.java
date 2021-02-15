package com.webmd.webmdrx.models.remote.pricepost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LowestPricePostModel {
    @SerializedName("request")
    @Expose
    private Request request;

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request2) {
        this.request = request2;
    }
}
