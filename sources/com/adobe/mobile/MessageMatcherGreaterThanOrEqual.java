package com.adobe.mobile;

import java.util.Iterator;

final class MessageMatcherGreaterThanOrEqual extends MessageMatcher {
    MessageMatcherGreaterThanOrEqual() {
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        Double tryParseDouble = tryParseDouble(obj);
        if (tryParseDouble == null) {
            return false;
        }
        Iterator it = this.values.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if ((next instanceof Number) && tryParseDouble.doubleValue() >= ((Number) next).doubleValue()) {
                return true;
            }
        }
        return false;
    }
}
