package com.webmd.webmdrx.models.remote.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Doclist {
    @SerializedName("docs")
    @Expose
    private List<Doc> docs = null;
    @SerializedName("numFound")
    @Expose
    private Integer numFound;
    @SerializedName("start")
    @Expose
    private Integer start;

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

    public List<Doc> getDocs() {
        return this.docs;
    }

    public void setDocs(List<Doc> list) {
        this.docs = list;
    }
}
