package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbvp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcxz<AdT, AdapterT, ListenerT extends zzbvp> implements zzeoy<zzcxv<AdT, AdapterT, ListenerT>> {
    private final zzeph<zzctb<AdapterT, ListenerT>> zzfmi;
    private final zzeph<zzdzb> zzfqk;
    private final zzeph<zzdsi> zzfqv;
    private final zzeph<zzcti<AdT, AdapterT, ListenerT>> zzgre;

    private zzcxz(zzeph<zzdsi> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzctb<AdapterT, ListenerT>> zzeph3, zzeph<zzcti<AdT, AdapterT, ListenerT>> zzeph4) {
        this.zzfqv = zzeph;
        this.zzfqk = zzeph2;
        this.zzfmi = zzeph3;
        this.zzgre = zzeph4;
    }

    public static <AdT, AdapterT, ListenerT extends zzbvp> zzcxz<AdT, AdapterT, ListenerT> zzf(zzeph<zzdsi> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzctb<AdapterT, ListenerT>> zzeph3, zzeph<zzcti<AdT, AdapterT, ListenerT>> zzeph4) {
        return new zzcxz<>(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzcxv(this.zzfqv.get(), this.zzfqk.get(), this.zzfmi.get(), this.zzgre.get());
    }
}
