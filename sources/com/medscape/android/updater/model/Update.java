package com.medscape.android.updater.model;

public class Update {
    private String mDestination;
    private boolean mIsCompressed;
    private boolean mIsPasswordProtected;
    private boolean mIsUserTryingToView;
    private String mMandatoryMessage = "";
    private String mOptionalMessage = "";
    private String mType;
    private String mUpdateMsg;
    private String mUpdateTitle;
    private String mUrl;

    public Update() {
    }

    public Update(String str, String str2, boolean z, String str3) {
        this.mType = str;
        this.mUrl = str2;
        this.mIsCompressed = z;
        this.mDestination = str3;
    }

    public String getFileName() {
        String str = this.mUrl;
        return str.substring(str.lastIndexOf("/") + 1);
    }

    public String getDestination() {
        return this.mDestination;
    }

    public void setDestination(String str) {
        this.mDestination = str;
    }

    public Update(String str, String str2, boolean z) {
        this.mType = str;
        this.mUrl = str2;
        this.mIsCompressed = z;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setIsCompressed(boolean z) {
        this.mIsCompressed = z;
    }

    public boolean isCompressed() {
        return this.mIsCompressed;
    }

    public void setPasswordProtected(boolean z) {
        this.mIsPasswordProtected = z;
    }

    public boolean isPasswordProtected() {
        return this.mIsPasswordProtected;
    }

    public String getOptionalMessage() {
        return this.mOptionalMessage;
    }

    public void setOptionalMessage(String str) {
        this.mOptionalMessage = str;
    }

    public String getMandatoryMessage() {
        return this.mMandatoryMessage;
    }

    public void setMandatoryMessage(String str) {
        this.mMandatoryMessage = str;
    }

    public boolean isUserTryingToView() {
        return this.mIsUserTryingToView;
    }

    public void setUserTryingToView(boolean z) {
        this.mIsUserTryingToView = z;
    }

    public String getmUpdateTitle() {
        return this.mUpdateTitle;
    }

    public void setmUpdateTitle(String str) {
        this.mUpdateTitle = str;
    }

    public String getmUpdateMsg() {
        return this.mUpdateMsg;
    }

    public void setmUpdateMsg(String str) {
        if (str != null) {
            this.mUpdateMsg = str.trim();
        }
    }
}
