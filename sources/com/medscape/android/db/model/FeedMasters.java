package com.medscape.android.db.model;

public class FeedMasters {
    private int specilatyId;
    private String specilatyName;

    public int getSpecilatyId() {
        return this.specilatyId;
    }

    public void setSpecilatyId(int i) {
        this.specilatyId = i;
    }

    public String getSpecilatyName() {
        return this.specilatyName;
    }

    public void setSpecilatyName(String str) {
        this.specilatyName = str;
    }

    public String toString() {
        return this.specilatyName + ":" + this.specilatyId;
    }
}
