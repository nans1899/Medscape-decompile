package com.webmd.webmdrx.models.remote.pricepost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drug {
    @SerializedName("ndc")
    @Expose
    private String ndc;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public String getNdc() {
        return this.ndc;
    }

    public void setNdc(String str) {
        this.ndc = str;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer num) {
        this.quantity = num;
    }
}
