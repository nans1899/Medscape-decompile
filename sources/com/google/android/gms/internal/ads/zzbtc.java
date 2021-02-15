package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtc implements zzeoy<zzcab<zzbws>> {
    private final zzeph<zzbsx> zzfop;
    private final zzbsz zzftj;

    private zzbtc(zzbsz zzbsz, zzeph<zzbsx> zzeph) {
        this.zzftj = zzbsz;
        this.zzfop = zzeph;
    }

    public static zzbtc zza(zzbsz zzbsz, zzeph<zzbsx> zzeph) {
        return new zzbtc(zzbsz, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
