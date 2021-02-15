package com.google.firebase.inappmessaging.model;

import com.google.firebase.inappmessaging.model.AutoValue_RateLimit;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public abstract class RateLimit {

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static abstract class Builder {
        public abstract RateLimit build();

        public abstract Builder setLimit(long j);

        public abstract Builder setLimiterKey(String str);

        public abstract Builder setTimeToLiveMillis(long j);
    }

    public abstract long limit();

    public abstract String limiterKey();

    public abstract long timeToLiveMillis();

    public static Builder builder() {
        return new AutoValue_RateLimit.Builder();
    }
}
