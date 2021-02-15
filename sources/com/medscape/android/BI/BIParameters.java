package com.medscape.android.BI;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class BIParameters {
    public static final String ACT = "ACT";
    public static final String DATA = "DATA";
    public static final String DTS = "DTS";
    public static final String ELPST = "ELPST";
    public static final String GUID = "GUID";
    public static final String MDL = "MDL";
    public static final String OS = "OS";
    public static final String PAG = "PAG";
    public static final String SECTION = "SECTION";
    public static final String UDID = "UDID";
    public static final String VER = "VER";
    private String act;
    private String data;
    private String dts;
    private String elpst;
    private String guid;
    private String mdl;
    private String os;
    private String pag;
    private String section;
    private String udid;
    private String ver;

    public String getMdl() {
        return this.mdl;
    }

    public void setMdl(String str) {
        this.mdl = str;
    }

    public String getPag() {
        return this.pag;
    }

    public void setPag(String str) {
        this.pag = str;
    }

    public String getAct() {
        return this.act;
    }

    public void setAct(String str) {
        this.act = str;
    }

    public String getDts() {
        return this.dts;
    }

    public void setDts(String str) {
        this.dts = str;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getVer() {
        return this.ver;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String str) {
        this.udid = str;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getData() {
        return this.data;
    }

    public void setSection(String str) {
        this.section = str;
    }

    public String getSection() {
        return this.section;
    }

    public void setElpst(String str) {
        this.elpst = str;
    }

    public String getElpst() {
        return this.elpst;
    }

    public String toString() {
        return this.pag + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.act + "  " + this.data;
    }
}
