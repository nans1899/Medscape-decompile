package com.webmd.webmdrx.models;

public class SmsResponseData {
    private boolean smsStatus;

    public void setSmsStatus(boolean z) {
        this.smsStatus = z;
    }

    public boolean isSmsStatus() {
        return this.smsStatus;
    }

    public String toString() {
        return "Data{sms_status = '" + this.smsStatus + '\'' + "}";
    }
}
