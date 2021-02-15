package com.webmd.webmdrx.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coupon {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private CouponData data;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public CouponData getData() {
        return this.data;
    }

    public void setData(CouponData couponData) {
        this.data = couponData;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
