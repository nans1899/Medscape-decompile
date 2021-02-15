package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyp implements zzeoy<zzcyn> {
    private final zzcyn zzgry;

    private zzcyp(zzcyn zzcyn) {
        this.zzgry = zzcyn;
    }

    public static zzcyp zzc(zzcyn zzcyn) {
        return new zzcyp(zzcyn);
    }

    public final /* synthetic */ Object get() {
        zzcyn zzcyn = this.zzgry;
        if (zzcyn != null) {
            return (zzcyn) zzepe.zza(zzcyn, "Cannot return null from a non-@Nullable @Provides method");
        }
        throw null;
    }
}
