package com.webmd.webmdrx.models.remote.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Coupon {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;
    @SerializedName("numFound")
    @Expose
    private Integer numFound;
    @SerializedName("start")
    @Expose
    private Integer start;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> list) {
        this.groups = list;
    }

    public Integer getNumFound() {
        return this.numFound;
    }

    public void setNumFound(Integer num) {
        this.numFound = num;
    }

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer num) {
        this.start = num;
    }
}
