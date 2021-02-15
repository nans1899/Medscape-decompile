package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzced {
    private zzadz zzckt;

    public zzced(zzcdv zzcdv) {
        this.zzckt = zzcdv;
    }

    public final synchronized zzadz zzsw() {
        return this.zzckt;
    }

    public final synchronized void zza(zzadz zzadz) {
        this.zzckt = zzadz;
    }
}
