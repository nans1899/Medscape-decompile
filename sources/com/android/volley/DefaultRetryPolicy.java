package com.android.volley;

public class DefaultRetryPolicy implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;

    public DefaultRetryPolicy() {
        this(5000, 1, 1.0f);
    }

    public DefaultRetryPolicy(int i, int i2, float f) {
        this.mCurrentTimeoutMs = i;
        this.mMaxNumRetries = i2;
        this.mBackoffMultiplier = f;
    }

    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    public float getBackoffMultiplier() {
        return this.mBackoffMultiplier;
    }

    public void retry(VolleyError volleyError) throws VolleyError {
        this.mCurrentRetryCount++;
        int i = this.mCurrentTimeoutMs;
        this.mCurrentTimeoutMs = (int) (((float) i) + (((float) i) * this.mBackoffMultiplier));
        if (!hasAttemptRemaining()) {
            throw volleyError;
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
