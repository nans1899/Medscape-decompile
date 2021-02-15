package com.adobe.mobile;

import java.util.Iterator;
import java.util.regex.Pattern;

class MessageMatcherContains extends MessageMatcher {
    MessageMatcherContains() {
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
            if ((next instanceof String) && Pattern.compile(Pattern.quote(next.toString()), 2).matcher(obj2).find()) {
                return true;
            }
        }
        return false;
    }
}
