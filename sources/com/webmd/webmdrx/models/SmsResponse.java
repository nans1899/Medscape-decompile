package com.webmd.webmdrx.models;

public class SmsResponse {
    private int code;
    private SmsResponseData data;
    private String status;

    public void setCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public void setData(SmsResponseData smsResponseData) {
        this.data = smsResponseData;
    }

    public SmsResponseData getData() {
        return this.data;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public String toString() {
        return "SmsResponse{code = '" + this.code + '\'' + ",data = '" + this.data + '\'' + ",status = '" + this.status + '\'' + "}";
    }
}
