package com.google.android.gms.ads.rewarded;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class ServerSideVerificationOptions {
    private final String zzdvz;
    private final String zzdwa;

    private ServerSideVerificationOptions(Builder builder) {
        this.zzdvz = builder.zzdvz;
        this.zzdwa = builder.zzdwa;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public String zzdvz = "";
        /* access modifiers changed from: private */
        public String zzdwa = "";

        public final Builder setUserId(String str) {
            this.zzdvz = str;
            return this;
        }

        public final Builder setCustomData(String str) {
            this.zzdwa = str;
            return this;
        }

        public final ServerSideVerificationOptions build() {
            return new ServerSideVerificationOptions(this);
        }
    }

    public String getUserId() {
        return this.zzdvz;
    }

    public String getCustomData() {
        return this.zzdwa;
    }
}
