package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrv implements zzeoy<zzcab<zzbvs>> {
    private final zzbrr zzfsy;
    private final zzeph<zzbsa> zzfsz;

    private zzbrv(zzbrr zzbrr, zzeph<zzbsa> zzeph) {
        this.zzfsy = zzbrr;
        this.zzfsz = zzeph;
    }

    public static zzbrv zzc(zzbrr zzbrr, zzeph<zzbsa> zzeph) {
        return new zzbrv(zzbrr, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfsz.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
