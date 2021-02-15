package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdps implements zzeoy<zzayr> {
    private final zzdpp zzhgo;
    private final zzeph<zzdpn> zzhgq;

    private zzdps(zzdpp zzdpp, zzeph<zzdpn> zzeph) {
        this.zzhgo = zzdpp;
        this.zzhgq = zzeph;
    }

    public static zzdps zza(zzdpp zzdpp, zzeph<zzdpn> zzeph) {
        return new zzdps(zzdpp, zzeph);
    }

    public static zzayr zza(zzdpp zzdpp, zzdpn zzdpn) {
        return (zzayr) zzepe.zza(zzdpn.zzdzr, "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzhgo, this.zzhgq.get());
    }
}
