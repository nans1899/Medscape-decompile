package com.wbmd.wbmddrugscommons.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Drug implements Serializable {
    private Boolean dedupe = false;
    @SerializedName("title")
    private String drugName;
    @SerializedName("drug_id_s")
    private String id;
    @SerializedName("istop_s")
    private Boolean isTop;
    @SerializedName("mono_id_s")
    private String monoId;
    @SerializedName("status_code_s")
    private String status;
    @SerializedName("url_s")
    private String urlSuffix;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setDrugName(String str) {
        this.drugName = str;
    }

    public String getMonoId() {
        return this.monoId;
    }

    public void setMonoId(String str) {
        this.monoId = str;
    }

    public String getUrlSuffix() {
        return this.urlSuffix;
    }

    public void setUrl(String str) {
        this.urlSuffix = str;
    }

    public Boolean getIsTop() {
        return this.isTop;
    }

    public void setIsTop(Boolean bool) {
        this.isTop = bool;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public Boolean getDedupe() {
        return this.dedupe;
    }

    public void setDedupe(Boolean bool) {
        this.dedupe = bool;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (getDedupe().booleanValue()) {
            return getId().equals(((Drug) obj).getId());
        }
        Drug drug = (Drug) obj;
        if (!getId().equals(drug.getId()) || !getMonoId().equals(drug.getMonoId())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getId() == null ? super.hashCode() : getId().hashCode();
    }
}
