package com.medscape.android.consult.models;

public class ConsultMention {
    private int mStart;
    private String mTextToReplace;
    private ConsultUser mUser;

    public void setUser(ConsultUser consultUser) {
        this.mUser = consultUser;
    }

    public ConsultUser getUser() {
        return this.mUser;
    }

    public void setTextToReplace(String str) {
        this.mTextToReplace = str;
    }

    public String getTextToReplace() {
        return this.mTextToReplace;
    }

    public void setStartPosition(int i) {
        this.mStart = i;
    }

    public int getStartPosition() {
        return this.mStart;
    }
}
