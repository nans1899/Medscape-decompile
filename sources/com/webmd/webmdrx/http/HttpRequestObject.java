package com.webmd.webmdrx.http;

import java.util.HashMap;

public class HttpRequestObject {
    private String authorization;
    private String contentType;
    private String cookies;
    private HashMap<String, String> mHeadersMap = new HashMap<>();
    private String requestBody;
    private String requestMethod;
    private int requestType;
    private String url;

    /* access modifiers changed from: package-private */
    public String getAuthorization() {
        return this.authorization;
    }

    public void setAuthorization(String str) {
        this.authorization = str;
    }

    /* access modifiers changed from: package-private */
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getCookies() {
        return this.cookies;
    }

    public void setCookies(String str) {
        this.cookies = str;
    }

    /* access modifiers changed from: package-private */
    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    /* access modifiers changed from: package-private */
    public int getRequestType() {
        return this.requestType;
    }

    public void setRequestType(int i) {
        this.requestType = i;
    }

    public String toString() {
        return "**********REQUEST-START**********\nurl>>" + this.url + "\ncookies>>" + this.cookies + "\nrequestType>>" + this.requestType + "\ncontentType>>" + this.contentType + "\n**********REQUEST-END**********";
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String str) {
        this.requestMethod = str;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(String str) {
        this.requestBody = str;
    }

    public HashMap<String, String> getHeadersMap() {
        return this.mHeadersMap;
    }

    public void addToHeadersMap(String str, String str2) {
        this.mHeadersMap.put(str, str2);
    }

    public void setHeaderMap(HashMap<String, String> hashMap) {
        this.mHeadersMap = hashMap;
    }
}
