package com.wbmd.wbmddatacompliance.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country")
    private String mCountry;
    @SerializedName("edgecountry")
    private String mEdgecountry;
    @SerializedName("edgecountrycode")
    private String mEdgecountrycode;
    @SerializedName("edgetwolettercountry")
    private String mEdgetwolettercountry;

    public String getCountry() {
        return this.mCountry;
    }

    public void setCountry(String str) {
        this.mCountry = str;
    }

    public String getEdgecountry() {
        return this.mEdgecountry;
    }

    public void setEdgecountry(String str) {
        this.mEdgecountry = str;
    }

    public String getEdgecountrycode() {
        return this.mEdgecountrycode;
    }

    public void setEdgecountrycode(String str) {
        this.mEdgecountrycode = str;
    }

    public String getEdgetwolettercountry() {
        return this.mEdgetwolettercountry;
    }

    public void setEdgetwolettercountry(String str) {
        this.mEdgetwolettercountry = str;
    }
}
