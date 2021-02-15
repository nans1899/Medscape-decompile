package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdco implements zzeoy<zzdcm> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzbbx> zzfup;

    private zzdco(zzeph<zzdzb> zzeph, zzeph<zzdok> zzeph2, zzeph<zzbbx> zzeph3) {
        this.zzesr = zzeph;
        this.zzfqr = zzeph2;
        this.zzfup = zzeph3;
    }

    public static zzdco zzs(zzeph<zzdzb> zzeph, zzeph<zzdok> zzeph2, zzeph<zzbbx> zzeph3) {
        return new zzdco(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdcm(this.zzesr.get(), this.zzfqr.get(), this.zzfup.get());
    }
}
