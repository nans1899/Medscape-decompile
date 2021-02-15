package com.adobe.mobile;

final class MessageMatcherNotContains extends MessageMatcherContains {
    MessageMatcherNotContains() {
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        return !super.matches(obj);
    }
}
