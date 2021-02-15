package com.tapstream.sdk.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class URLEncoding {
    public static final StringEncoder FORM_FIELD_ENCODER = new FormFieldEncoder(true);
    public static final StringEncoder QUERY_STRING_ENCODER = new FormFieldEncoder(false);

    public interface StringEncoder {
        String encode(String str);
    }

    public static class FormFieldEncoder implements StringEncoder {
        private final boolean plusForSpace;

        public FormFieldEncoder(boolean z) {
            this.plusForSpace = z;
        }

        public String encode(String str) {
            try {
                String encode = URLEncoder.encode(str, "UTF-8");
                if (this.plusForSpace) {
                    return encode;
                }
                return encode.replace("+", "%20");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String joinAndEncodeParams(Map<String, String> map, StringEncoder stringEncoder) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append(stringEncoder.encode((String) next.getKey()));
            if (next.getValue() != null) {
                sb.append("=");
                sb.append(stringEncoder.encode((String) next.getValue()));
            }
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public static String buildQueryString(Map<String, String> map) {
        return joinAndEncodeParams(map, QUERY_STRING_ENCODER);
    }

    public static String buildFormBody(Map<String, String> map) {
        return joinAndEncodeParams(map, FORM_FIELD_ENCODER);
    }
}
