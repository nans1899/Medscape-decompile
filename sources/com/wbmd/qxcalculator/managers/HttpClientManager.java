package com.wbmd.qxcalculator.managers;

import okhttp3.OkHttpClient;

public class HttpClientManager {
    private static final HttpClientManager ourInstance = new HttpClientManager();
    private OkHttpClient client;

    public static HttpClientManager getInstance() {
        return ourInstance;
    }

    private HttpClientManager() {
    }

    public OkHttpClient getHttpClient() {
        if (this.client == null) {
            this.client = new OkHttpClient.Builder().build();
        }
        return this.client;
    }
}
