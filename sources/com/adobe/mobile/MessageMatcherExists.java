package com.adobe.mobile;

import java.util.Map;

class MessageMatcherExists extends MessageMatcher {
    MessageMatcherExists() {
    }

    /* access modifiers changed from: protected */
    public boolean matchesInMaps(Map<String, Object>... mapArr) {
        Object obj;
        if (mapArr == null || mapArr.length <= 0) {
            return false;
        }
        int length = mapArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                obj = null;
                break;
            }
            Map<String, Object> map = mapArr[i];
            if (map != null && map.containsKey(this.key)) {
                obj = map.get(this.key);
                break;
            }
            i++;
        }
        if (obj != null) {
            return true;
        }
        return false;
    }
}
