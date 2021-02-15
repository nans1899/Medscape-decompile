package com.medscape.android.http;

import java.util.HashMap;

public class HttpResponseObject {
    private int requestType;
    private int responseCode;
    private HashMap<String, String> responseCookies;
    private String responseData;
    private String responseErrorMsg;

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponseData() {
        return this.responseData;
    }

    public void setResponseData(String str) {
        this.responseData = str;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public int getRequestType() {
        return this.requestType;
    }

    public void setRequestType(int i) {
        this.requestType = i;
    }

    public String getResponseErrorMsg() {
        return this.responseErrorMsg;
    }

    public void setResponseErrorMsg(String str) {
        this.responseErrorMsg = str;
    }

    public String toString() {
        return "**********RESPONSE-START**********\nresponseCode>>" + this.responseCode + "\nrequestType>>" + this.requestType + "\nresponseData>>" + this.responseData + "\nresponseErrorMsg>>" + this.responseErrorMsg + "\n**********RESPONSE-END**********";
    }

    public HashMap<String, String> getResponseCookies() {
        return this.responseCookies;
    }

    public void setResponseCookies(HashMap<String, String> hashMap) {
        this.responseCookies = hashMap;
    }
}
