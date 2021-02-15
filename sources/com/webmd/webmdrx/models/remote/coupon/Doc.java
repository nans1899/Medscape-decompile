package com.webmd.webmdrx.models.remote.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Doc {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rules_data_mvs")
    @Expose
    private List<String> rulesDataMvs = null;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<String> getRulesDataMvs() {
        return this.rulesDataMvs;
    }

    public void setRulesDataMvs(List<String> list) {
        this.rulesDataMvs = list;
    }
}
