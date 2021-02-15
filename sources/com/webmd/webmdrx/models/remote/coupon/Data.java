package com.webmd.webmdrx.models.remote.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("coupon")
    @Expose
    private Coupon coupon;
    @SerializedName("query")
    @Expose
    private Query query;

    public Coupon getCoupon() {
        return this.coupon;
    }

    public void setCoupon(Coupon coupon2) {
        this.coupon = coupon2;
    }

    public Query getQuery() {
        return this.query;
    }

    public void setQuery(Query query2) {
        this.query = query2;
    }
}
