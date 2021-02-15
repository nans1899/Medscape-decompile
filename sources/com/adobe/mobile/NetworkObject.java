package com.adobe.mobile;

import java.util.List;
import java.util.Map;

final class NetworkObject {
    protected String response;
    protected int responseCode;
    protected Map<String, List<String>> responseHeaders;

    NetworkObject() {
    }
}
