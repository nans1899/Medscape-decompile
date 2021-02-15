package com.webmd.webmdrx.models.remote.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {
    @SerializedName("query")
    @Expose
    private String query;

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }
}
