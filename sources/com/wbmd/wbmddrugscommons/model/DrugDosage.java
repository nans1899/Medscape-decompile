package com.wbmd.wbmddrugscommons.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class DrugDosage implements Serializable {
    @SerializedName("dispensable_drug_id")
    private String dispensableDrugId;
    @SerializedName("dose_forms")
    private String forms;
    @SerializedName("monograph_ids")
    private ArrayList<String> monographIds;
    @SerializedName("name")
    private String name;
    @SerializedName("route")
    private String route;
    @SerializedName("dispensable_drug_status_code")
    private String statusCode;
    @SerializedName("med_strength")
    private String strength;
    @SerializedName("tag")
    private String tag;
    @SerializedName("type")
    private String type;
    @SerializedName("med_unit")
    private String unit;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getForms() {
        return this.forms;
    }

    public void setForms(String str) {
        this.forms = str;
    }

    public String getStrength() {
        return this.strength;
    }

    public void setStrength(String str) {
        this.strength = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String str) {
        this.route = str;
    }

    public String getDispensableDrugId() {
        return this.dispensableDrugId;
    }

    public void setDispensableDrugId(String str) {
        this.dispensableDrugId = str;
    }

    public ArrayList<String> getMonographIds() {
        return this.monographIds;
    }

    public void setMonographIds(ArrayList<String> arrayList) {
        this.monographIds = arrayList;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }
}
