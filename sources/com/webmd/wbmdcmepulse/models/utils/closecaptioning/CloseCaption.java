package com.webmd.wbmdcmepulse.models.utils.closecaptioning;

public class CloseCaption {
    private String captionString;
    private long endTime;
    private long startTime;

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public String getCaptionString() {
        return this.captionString;
    }

    public void setCaptionString(String str) {
        this.captionString = str;
    }
}
