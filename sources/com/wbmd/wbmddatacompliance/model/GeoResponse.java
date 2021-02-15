package com.wbmd.wbmddatacompliance.model;

import com.google.gson.annotations.SerializedName;

public class GeoResponse {
    @SerializedName("code")
    private Long mCode;
    @SerializedName("data")
    private GeoResponseData mData;
    @SerializedName("status")
    private String mStatus;

    public Long getCode() {
        return this.mCode;
    }

    public void setCode(Long l) {
        this.mCode = l;
    }

    public GeoResponseData getData() {
        return this.mData;
    }

    public void setData(GeoResponseData geoResponseData) {
        this.mData = geoResponseData;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }
}
