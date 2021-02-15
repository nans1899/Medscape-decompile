package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcey implements zzeoy<zzawp> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdok> zzfqr;
    private final zzceu zzgar;

    private zzcey(zzceu zzceu, zzeph<Context> zzeph, zzeph<zzdok> zzeph2) {
        this.zzgar = zzceu;
        this.zzesu = zzeph;
        this.zzfqr = zzeph2;
    }

    public static zzcey zza(zzceu zzceu, zzeph<Context> zzeph, zzeph<zzdok> zzeph2) {
        return new zzcey(zzceu, zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzawp) zzepe.zza(new zzawp(this.zzesu.get(), this.zzfqr.get().zzhfb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
