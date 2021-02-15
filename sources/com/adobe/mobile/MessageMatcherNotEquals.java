package com.adobe.mobile;

final class MessageMatcherNotEquals extends MessageMatcherEquals {
    MessageMatcherNotEquals() {
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        return !super.matches(obj);
    }
}
