package com.adobe.mobile;

import java.util.Map;

class MessageMatcherUnknown extends MessageMatcher {
    /* access modifiers changed from: protected */
    public boolean matchesInMaps(Map<String, Object>... mapArr) {
        return false;
    }

    MessageMatcherUnknown() {
    }
}
