package com.medscape.android.db.model;

public class FeedDetails {
    private int feedType;
    private int specilatyId;
    private String url;

    public int getSpecilatyId() {
        return this.specilatyId;
    }

    public void setSpecilatyId(int i) {
        this.specilatyId = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getFeedType() {
        return this.feedType;
    }

    public void setFeedType(int i) {
        this.feedType = i;
    }
}
