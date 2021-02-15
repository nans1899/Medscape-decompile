package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgc implements zzeoy<zzdfz> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzcza> zzeti;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<String> zzfow;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzcyy> zzgra;

    private zzdgc(zzeph<zzdzb> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<String> zzeph3, zzeph<zzcza> zzeph4, zzeph<Context> zzeph5, zzeph<zzdok> zzeph6, zzeph<zzcyy> zzeph7) {
        this.zzesr = zzeph;
        this.zzfnm = zzeph2;
        this.zzfow = zzeph3;
        this.zzeti = zzeph4;
        this.zzesu = zzeph5;
        this.zzfqr = zzeph6;
        this.zzgra = zzeph7;
    }

    public static zzdgc zzb(zzeph<zzdzb> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<String> zzeph3, zzeph<zzcza> zzeph4, zzeph<Context> zzeph5, zzeph<zzdok> zzeph6, zzeph<zzcyy> zzeph7) {
        return new zzdgc(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7);
    }

    public final /* synthetic */ Object get() {
        return new zzdfz(this.zzesr.get(), this.zzfnm.get(), this.zzfow.get(), this.zzeti.get(), this.zzesu.get(), this.zzfqr.get(), this.zzgra.get());
    }
}
