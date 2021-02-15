package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbth implements zzeoy<zzbwk> {
    private final zzeph<zzbwk> zzftr;

    private zzbth(zzeph<zzbwk> zzeph) {
        this.zzftr = zzeph;
    }

    public static zzbth zzf(zzeph<zzbwk> zzeph) {
        return new zzbth(zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzbwk) zzepe.zza(this.zzftr.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
