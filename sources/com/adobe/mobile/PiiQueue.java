package com.adobe.mobile;

final class PiiQueue extends ThirdPartyQueue {
    private static final String PII_HIT_CACHE = "ADBMobilePIICache.sqlite";
    private static PiiQueue _instance;
    private static final Object _instanceMutex = new Object();

    /* access modifiers changed from: protected */
    public String fileName() {
        return PII_HIT_CACHE;
    }

    /* access modifiers changed from: protected */
    public String logPrefix() {
        return "PII";
    }

    /* access modifiers changed from: protected */
    public String threadSuffix() {
        return "pii";
    }

    protected PiiQueue() {
    }

    protected static PiiQueue sharedInstance() {
        PiiQueue piiQueue;
        synchronized (_instanceMutex) {
            if (_instance == null) {
                _instance = new PiiQueue();
            }
            piiQueue = _instance;
        }
        return piiQueue;
    }

    /* access modifiers changed from: protected */
    public ThirdPartyQueue getWorker() {
        return sharedInstance();
    }
}
