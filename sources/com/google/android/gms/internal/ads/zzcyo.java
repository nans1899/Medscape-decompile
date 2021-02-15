package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyo<AdT> implements zzeoy<zzcyk<AdT>> {
    private final zzeph<zzdzb> zzfqk;
    private final zzeph<zzdsi> zzfqv;
    private final zzeph<zzaby> zzgrq;
    private final zzeph<zzcyl<AdT>> zzgrx;

    public zzcyo(zzeph<zzdsi> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzaby> zzeph3, zzeph<zzcyl<AdT>> zzeph4) {
        this.zzfqv = zzeph;
        this.zzfqk = zzeph2;
        this.zzgrq = zzeph3;
        this.zzgrx = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzcyk(this.zzfqv.get(), this.zzfqk.get(), this.zzgrq.get(), this.zzgrx.get());
    }
}
