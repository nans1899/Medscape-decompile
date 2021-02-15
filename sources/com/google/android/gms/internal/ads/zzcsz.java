package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsz<DelegateT, AdapterT> implements zzcta<AdapterT> {
    private final zzcta<DelegateT> zzgnq;
    private final zzdvu<DelegateT, AdapterT> zzgnr;

    public zzcsz(zzcta<DelegateT> zzcta, zzdvu<DelegateT, AdapterT> zzdvu) {
        this.zzgnq = zzcta;
        this.zzgnr = zzdvu;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return this.zzgnq.zza(zzdog, zzdnv);
    }

    public final zzdzc<AdapterT> zzb(zzdog zzdog, zzdnv zzdnv) {
        return zzdyq.zzb(this.zzgnq.zzb(zzdog, zzdnv), this.zzgnr, (Executor) zzbbz.zzeep);
    }
}
