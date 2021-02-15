package com.adobe.mobile;

import java.util.Iterator;

class MessageMatcherEquals extends MessageMatcher {
    MessageMatcherEquals() {
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        Iterator it = this.values.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            boolean z = next instanceof String;
            if (!z || !(obj instanceof String)) {
                boolean z2 = next instanceof Number;
                if (!z2 || !(obj instanceof Number)) {
                    if (!z2 || !(obj instanceof String)) {
                        if (z && (obj instanceof Number) && next.toString().compareToIgnoreCase(obj.toString()) == 0) {
                            return true;
                        }
                    } else if (tryParseDouble(obj) != null && ((Number) next).doubleValue() == tryParseDouble(obj).doubleValue()) {
                        return true;
                    }
                } else if (((Number) next).doubleValue() == ((Number) obj).doubleValue()) {
                    return true;
                }
            } else if (next.toString().compareToIgnoreCase(obj.toString()) == 0) {
                return true;
            }
        }
        return false;
    }
}
