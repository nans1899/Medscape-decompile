package com.medscape.android.ads.proclivity;

import java.util.HashMap;
import java.util.Map;

public class ProclivityDataModel {
    String asid;
    int height;
    Map<String, String> otherData;
    int width;

    public ProclivityDataModel() {
        this.width = 0;
        this.height = 0;
        this.asid = "";
        this.otherData = new HashMap();
    }

    public ProclivityDataModel(int i, int i2, String str) {
        this.width = i;
        this.height = i2;
        this.asid = str;
        this.otherData = new HashMap();
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getAsid() {
        return this.asid;
    }

    public void setAsid(String str) {
        this.asid = str;
    }

    public Map<String, String> getOtherData() {
        return this.otherData;
    }

    public void setOtherData(Map<String, String> map) {
        this.otherData = map;
    }

    public Map<String, String> getProclivityMap() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.otherData);
        String str = this.asid;
        if (str != null) {
            hashMap.put("asid", str);
        } else {
            hashMap.put("asid", "");
        }
        return hashMap;
    }
}
