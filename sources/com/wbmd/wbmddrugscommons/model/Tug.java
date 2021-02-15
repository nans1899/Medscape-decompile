package com.wbmd.wbmddrugscommons.model;

import java.io.Serializable;

public class Tug implements Serializable {
    private String brand;
    private String client;
    private String end_date;
    private String id;
    private String package_type;
    private String program;
    private String start_date;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getProgram() {
        return this.program;
    }

    public void setProgram(String str) {
        this.program = str;
    }

    public String getPackageType() {
        return this.package_type;
    }

    public void setPackageType(String str) {
        this.package_type = str;
    }

    public String getStartDate() {
        return this.start_date;
    }

    public void setStartDate(String str) {
        this.start_date = str;
    }

    public String getEndDate() {
        return this.end_date;
    }

    public void setEndDate(String str) {
        this.end_date = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String str) {
        this.client = str;
    }
}
