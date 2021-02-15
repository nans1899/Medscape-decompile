package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpu implements zzeoy<Context> {
    private final zzdpp zzhgo;
    private final zzeph<zzdpn> zzhgq;

    private zzdpu(zzdpp zzdpp, zzeph<zzdpn> zzeph) {
        this.zzhgo = zzdpp;
        this.zzhgq = zzeph;
    }

    public static zzdpu zzc(zzdpp zzdpp, zzeph<zzdpn> zzeph) {
        return new zzdpu(zzdpp, zzeph);
    }

    public static Context zzb(zzdpp zzdpp, zzdpn zzdpn) {
        return (Context) zzepe.zza(zzdpn.zzaai, "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzb(this.zzhgo, this.zzhgq.get());
    }
}
