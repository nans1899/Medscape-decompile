package com.webmd.webmdrx.models.remote.priceresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("lowestprice")
    @Expose
    private Double lowestprice;
    @SerializedName("ndc")
    @Expose
    private String ndc;
    @SerializedName("qty")
    @Expose
    private Integer qty;

    public String getNdc() {
        return this.ndc;
    }

    public void setNdc(String str) {
        this.ndc = str;
    }

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer num) {
        this.qty = num;
    }

    public Double getLowestprice() {
        return this.lowestprice;
    }

    public void setLowestprice(Double d) {
        this.lowestprice = d;
    }
}
