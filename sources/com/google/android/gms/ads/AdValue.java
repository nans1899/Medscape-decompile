package com.google.android.gms.ads;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdValue {
    private final int zzadc;
    private final String zzadd;
    private final long zzade;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface PrecisionType {
        public static final int ESTIMATED = 1;
        public static final int PRECISE = 3;
        public static final int PUBLISHER_PROVIDED = 2;
        public static final int UNKNOWN = 0;
    }

    private AdValue(int i, String str, long j) {
        this.zzadc = i;
        this.zzadd = str;
        this.zzade = j;
    }

    public static AdValue zza(int i, String str, long j) {
        return new AdValue(i, str, j);
    }

    public final int getPrecisionType() {
        return this.zzadc;
    }

    public final String getCurrencyCode() {
        return this.zzadd;
    }

    public final long getValueMicros() {
        return this.zzade;
    }
}
