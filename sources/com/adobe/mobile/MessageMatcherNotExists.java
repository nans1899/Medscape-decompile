package com.adobe.mobile;

import java.util.Map;

class MessageMatcherNotExists extends MessageMatcherExists {
    MessageMatcherNotExists() {
    }

    /* access modifiers changed from: protected */
    public boolean matchesInMaps(Map<String, Object>... mapArr) {
        return !super.matchesInMaps(mapArr);
    }
}
