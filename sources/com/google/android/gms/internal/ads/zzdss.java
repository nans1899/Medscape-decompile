package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdss implements zzeoy<zzamb> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfup;
    private final zzdsp zzhlm;

    public zzdss(zzdsp zzdsp, zzeph<Context> zzeph, zzeph<zzbbx> zzeph2) {
        this.zzhlm = zzdsp;
        this.zzesu = zzeph;
        this.zzfup = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzamb) zzepe.zza(new zzalw().zzb(this.zzesu.get(), this.zzfup.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
