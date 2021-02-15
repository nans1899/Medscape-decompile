package com.tapstream.sdk.http;

import com.dd.plist.ASCIIPropertyListParser;
import com.tapstream.sdk.Retry;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;

public class HttpRequest {
    private final RequestBody body;
    private final HttpMethod method;
    private final URL url;

    public HttpRequest(URL url2, HttpMethod httpMethod, RequestBody requestBody) {
        this.url = url2;
        this.method = httpMethod;
        this.body = requestBody;
    }

    public URL getURL() {
        return this.url;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public RequestBody getBody() {
        return this.body;
    }

    public Retry.Retryable<HttpRequest> makeRetryable(Retry.Strategy strategy) {
        return new Retry.Retryable<>(this, strategy);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpRequest httpRequest = (HttpRequest) obj;
        URL url2 = this.url;
        if (url2 == null ? httpRequest.url != null : !url2.equals(httpRequest.url)) {
            return false;
        }
        if (this.method != httpRequest.method) {
            return false;
        }
        RequestBody requestBody = this.body;
        RequestBody requestBody2 = httpRequest.body;
        if (requestBody != null) {
            return requestBody.equals(requestBody2);
        }
        if (requestBody2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        URL url2 = this.url;
        int i = 0;
        int hashCode = (url2 != null ? url2.hashCode() : 0) * 31;
        HttpMethod httpMethod = this.method;
        int hashCode2 = (hashCode + (httpMethod != null ? httpMethod.hashCode() : 0)) * 31;
        RequestBody requestBody = this.body;
        if (requestBody != null) {
            i = requestBody.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "HttpRequest{url=" + this.url + ", method=" + this.method + ", body=" + this.body + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }

    public static class Builder {
        private RequestBody body;
        private String fragment;
        private String host;
        private HttpMethod method;
        private String path;
        private Map<String, String> qs = new LinkedHashMap();
        private String scheme;

        public Builder method(HttpMethod httpMethod) {
            this.method = httpMethod;
            return this;
        }

        public Builder scheme(String str) {
            this.scheme = str;
            return this;
        }

        public Builder host(String str) {
            this.host = str;
            return this;
        }

        public Builder path(String str) {
            this.path = str;
            return this;
        }

        public Builder fragment(String str) {
            this.fragment = str;
            return this;
        }

        public Builder addQueryParameter(String str, String str2) {
            this.qs.put(str, str2);
            return this;
        }

        public Builder addQueryParameters(Map<String, String> map) {
            this.qs.putAll(map);
            return this;
        }

        public Builder postBody(RequestBody requestBody) {
            this.body = requestBody;
            return this;
        }

        public HttpRequest build() throws MalformedURLException {
            if (this.scheme == null) {
                throw new NullPointerException("Scheme must not be null");
            } else if (this.host == null) {
                throw new NullPointerException("Host must not be null");
            } else if (this.method != null) {
                StringBuilder sb = new StringBuilder(this.scheme + "://" + this.host);
                String str = this.path;
                if (str != null) {
                    if (!str.startsWith("/")) {
                        sb.append("/");
                    }
                    sb.append(this.path);
                }
                Map<String, String> map = this.qs;
                if (map != null && !map.isEmpty()) {
                    sb.append(TypeDescription.Generic.OfWildcardType.SYMBOL);
                    sb.append(URLEncoding.buildQueryString(this.qs));
                }
                String str2 = this.fragment;
                if (str2 != null && !str2.isEmpty()) {
                    sb.append("#");
                    sb.append(this.fragment);
                }
                return new HttpRequest(new URL(sb.toString()), this.method, this.body);
            } else {
                throw new NullPointerException("Method must not be null");
            }
        }
    }
}
