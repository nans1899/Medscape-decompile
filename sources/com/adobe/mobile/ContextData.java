package com.adobe.mobile;

import java.util.HashMap;

final class ContextData {
    protected HashMap<String, Object> contextData = new HashMap<>();
    protected Object value = null;

    ContextData() {
    }

    public synchronized String toString() {
        String str;
        str = "";
        if (this.value != null) {
            str = this.value.toString();
        }
        return super.toString() + str;
    }

    /* access modifiers changed from: protected */
    public boolean containsKey(String str) {
        return this.contextData.containsKey(str);
    }

    /* access modifiers changed from: protected */
    public void put(String str, ContextData contextData2) {
        this.contextData.put(str, contextData2);
    }

    /* access modifiers changed from: protected */
    public ContextData get(String str) {
        return (ContextData) this.contextData.get(str);
    }
}
