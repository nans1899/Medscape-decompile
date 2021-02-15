package com.google.firebase.inappmessaging.model;

import com.google.firebase.inappmessaging.model.RateLimit;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final class AutoValue_RateLimit extends RateLimit {
    private final long limit;
    private final String limiterKey;
    private final long timeToLiveMillis;

    private AutoValue_RateLimit(String str, long j, long j2) {
        this.limiterKey = str;
        this.limit = j;
        this.timeToLiveMillis = j2;
    }

    public String limiterKey() {
        return this.limiterKey;
    }

    public long limit() {
        return this.limit;
    }

    public long timeToLiveMillis() {
        return this.timeToLiveMillis;
    }

    public String toString() {
        return "RateLimit{limiterKey=" + this.limiterKey + ", limit=" + this.limit + ", timeToLiveMillis=" + this.timeToLiveMillis + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RateLimit)) {
            return false;
        }
        RateLimit rateLimit = (RateLimit) obj;
        if (this.limiterKey.equals(rateLimit.limiterKey()) && this.limit == rateLimit.limit() && this.timeToLiveMillis == rateLimit.timeToLiveMillis()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.limit;
        long j2 = this.timeToLiveMillis;
        return ((((this.limiterKey.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static final class Builder extends RateLimit.Builder {
        private Long limit;
        private String limiterKey;
        private Long timeToLiveMillis;

        Builder() {
        }

        public RateLimit.Builder setLimiterKey(String str) {
            if (str != null) {
                this.limiterKey = str;
                return this;
            }
            throw new NullPointerException("Null limiterKey");
        }

        public RateLimit.Builder setLimit(long j) {
            this.limit = Long.valueOf(j);
            return this;
        }

        public RateLimit.Builder setTimeToLiveMillis(long j) {
            this.timeToLiveMillis = Long.valueOf(j);
            return this;
        }

        public RateLimit build() {
            String str = "";
            if (this.limiterKey == null) {
                str = str + " limiterKey";
            }
            if (this.limit == null) {
                str = str + " limit";
            }
            if (this.timeToLiveMillis == null) {
                str = str + " timeToLiveMillis";
            }
            if (str.isEmpty()) {
                return new AutoValue_RateLimit(this.limiterKey, this.limit.longValue(), this.timeToLiveMillis.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
