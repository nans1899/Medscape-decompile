package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrw implements zzeoy<zzcab<zzbuz>> {
    private final zzbrr zzfsy;
    private final zzeph<zzbsa> zzfsz;

    private zzbrw(zzbrr zzbrr, zzeph<zzbsa> zzeph) {
        this.zzfsy = zzbrr;
        this.zzfsz = zzeph;
    }

    public static zzbrw zzd(zzbrr zzbrr, zzeph<zzbsa> zzeph) {
        return new zzbrw(zzbrr, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfsz.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
