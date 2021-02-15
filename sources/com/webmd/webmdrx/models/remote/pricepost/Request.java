package com.webmd.webmdrx.models.remote.pricepost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Request {
    @SerializedName("drugs")
    @Expose
    private List<Drug> drugs = null;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("radius")
    @Expose
    private Integer radius;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double d) {
        this.lat = d;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double d) {
        this.lng = d;
    }

    public Integer getRadius() {
        return this.radius;
    }

    public void setRadius(Integer num) {
        this.radius = num;
    }

    public List<Drug> getDrugs() {
        return this.drugs;
    }

    public void setDrugs(List<Drug> list) {
        this.drugs = list;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String str) {
        this.zipcode = str;
    }
}
