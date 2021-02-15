package com.webmd.webmdrx.models;

public class RxDrugDetail {
    String drugName = "";
    String fdbId = "";
    String gp10Id = "";
    String monoId = "";

    public String getFdbId() {
        return this.fdbId;
    }

    public void setFdbId(String str) {
        this.fdbId = str;
    }

    public String getMonoId() {
        return this.monoId;
    }

    public void setMonoId(String str) {
        this.monoId = str;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setDrugName(String str) {
        this.drugName = str;
    }

    public String getGp10Id() {
        return this.gp10Id;
    }

    public void setGp10Id(String str) {
        this.gp10Id = str;
    }
}
