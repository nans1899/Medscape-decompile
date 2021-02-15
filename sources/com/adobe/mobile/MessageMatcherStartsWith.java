package com.adobe.mobile;

import java.util.Iterator;
import java.util.regex.Pattern;

final class MessageMatcherStartsWith extends MessageMatcher {
    MessageMatcherStartsWith() {
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        boolean z = obj instanceof Number;
        if (!(obj instanceof String) && !z) {
            return false;
        }
        String obj2 = obj.toString();
        Iterator it = this.values.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof String) {
                if (obj2.matches("(?i)" + Pattern.quote(next.toString()) + ".*")) {
                    return true;
                }
            }
        }
        return false;
    }
}
