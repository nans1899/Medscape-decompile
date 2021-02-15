package com.webmd.wbmdproffesionalauthentication.model;

import java.util.Map;

public class Speciality {
    private String mId;
    private String mName;
    private Map<String, String> mSubSpecialities;

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public Map<String, String> getSubSpecialities() {
        return this.mSubSpecialities;
    }

    public void setSubSpecialities(Map<String, String> map) {
        this.mSubSpecialities = map;
    }
}
