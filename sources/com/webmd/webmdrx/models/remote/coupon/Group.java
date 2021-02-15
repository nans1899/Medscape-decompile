package com.webmd.webmdrx.models.remote.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("doclist")
    @Expose
    private Doclist doclist;
    @SerializedName("groupValue")
    @Expose
    private String groupValue;

    public String getGroupValue() {
        return this.groupValue;
    }

    public void setGroupValue(String str) {
        this.groupValue = str;
    }

    public Doclist getDoclist() {
        return this.doclist;
    }

    public void setDoclist(Doclist doclist2) {
        this.doclist = doclist2;
    }
}
