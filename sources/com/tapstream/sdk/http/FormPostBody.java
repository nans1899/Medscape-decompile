package com.tapstream.sdk.http;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormPostBody implements RequestBody {
    private final Map<String, String> params = new LinkedHashMap();

    public String contentType() {
        return "application/x-www-form-urlencoded; charset=utf-8";
    }

    public FormPostBody add(String str, String str2) {
        this.params.put(str, str2);
        return this;
    }

    public FormPostBody add(Map<String, String> map) {
        this.params.putAll(map);
        return this;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public byte[] toBytes() {
        try {
            return URLEncoding.buildFormBody(this.params).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "FormPostBody{params=" + this.params + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Map<String, String> map = this.params;
        Map<String, String> map2 = ((FormPostBody) obj).params;
        if (map != null) {
            return map.equals(map2);
        }
        if (map2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Map<String, String> map = this.params;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }
}
