package com.tapstream.sdk;

public class AdvertisingID {
    private final String id;
    private final boolean limitAdTracking;

    public AdvertisingID(String str, boolean z) {
        this.id = str;
        this.limitAdTracking = z;
    }

    public String getId() {
        return this.id;
    }

    public boolean isLimitAdTracking() {
        return this.limitAdTracking;
    }

    public boolean isValid() {
        String str = this.id;
        return str != null && str.length() > 0;
    }
}
