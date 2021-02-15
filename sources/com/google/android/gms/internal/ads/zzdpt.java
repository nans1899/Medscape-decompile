package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpt implements zzeoy<zzayi> {
    private final zzdpp zzhgo;
    private final zzeph<zzdpn> zzhgq;

    private zzdpt(zzdpp zzdpp, zzeph<zzdpn> zzeph) {
        this.zzhgo = zzdpp;
        this.zzhgq = zzeph;
    }

    public static zzdpt zzb(zzdpp zzdpp, zzeph<zzdpn> zzeph) {
        return new zzdpt(zzdpp, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzayi) zzepe.zza(this.zzhgq.get().zzdyh, "Cannot return null from a non-@Nullable @Provides method");
    }
}
