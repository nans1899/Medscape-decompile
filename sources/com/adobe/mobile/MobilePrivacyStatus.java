package com.adobe.mobile;

public enum MobilePrivacyStatus {
    MOBILE_PRIVACY_STATUS_OPT_IN(0),
    MOBILE_PRIVACY_STATUS_OPT_OUT(1),
    MOBILE_PRIVACY_STATUS_UNKNOWN(2);
    
    private final int value;

    private MobilePrivacyStatus(int i) {
        this.value = i;
    }

    /* access modifiers changed from: protected */
    public int getValue() {
        return this.value;
    }
}
