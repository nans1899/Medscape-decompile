package com.medscape.android.consult.models;

public class ZimbraConfigResponse {
    private String mErrorFromServer;
    private String mForumId;
    private String mGroupId;
    private String mHiddenForumId;
    private String mMediaGalleryId;

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public void setForumId(String str) {
        this.mForumId = str;
    }

    public String getForumId() {
        return this.mForumId;
    }

    public void setMediaGalleryId(String str) {
        this.mMediaGalleryId = str;
    }

    public String getMediaGalleryId() {
        return this.mMediaGalleryId;
    }

    public void setHiddenForumId(String str) {
        this.mHiddenForumId = str;
    }

    public String getHiddenForumId() {
        return this.mHiddenForumId;
    }

    public void setErrorFromServer(String str) {
        this.mErrorFromServer = str;
    }

    public String getErrorFromServer() {
        return this.mErrorFromServer;
    }
}
