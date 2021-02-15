package com.wbmd.wbmddatacompliance.model;

import com.google.gson.annotations.SerializedName;

public class GeoResponseLegacy {
    @SerializedName("code")
    private Long mCode;
    @SerializedName("data")
    private String mData;
    @SerializedName("status")
    private String mStatus;

    public Long getCode() {
        return this.mCode;
    }

    public void setCode(Long l) {
        this.mCode = l;
    }

    public String getData() {
        return this.mData;
    }

    public void setData(String str) {
        this.mData = str;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }
}
