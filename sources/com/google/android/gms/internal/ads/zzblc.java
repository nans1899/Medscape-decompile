package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzblc implements zzeoy<zzams> {
    private final zzbky zzflz;

    public zzblc(zzbky zzbky) {
        this.zzflz = zzbky;
    }

    public static zzams zzb(zzbky zzbky) {
        return (zzams) zzepe.zza(zzbky.zzaen(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzb(this.zzflz);
    }
}
