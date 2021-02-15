package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsi implements zzeoy<zzbqn> {
    private final zzeph<zzbvb> zzfbx;
    private final zzeph<zzbwv> zzfby;
    private final zzeph<zzbvr> zzfdf;
    private final zzeph<zzdog> zzfnn;
    private final zzeph<zzdnv> zzfrj;
    private final zzeph<zzdls> zzftc;
    private final zzeph<zzbty> zzftd;

    private zzbsi(zzeph<zzdog> zzeph, zzeph<zzdnv> zzeph2, zzeph<zzbvb> zzeph3, zzeph<zzbvr> zzeph4, zzeph<zzdls> zzeph5, zzeph<zzbty> zzeph6, zzeph<zzbwv> zzeph7) {
        this.zzfnn = zzeph;
        this.zzfrj = zzeph2;
        this.zzfbx = zzeph3;
        this.zzfdf = zzeph4;
        this.zzftc = zzeph5;
        this.zzftd = zzeph6;
        this.zzfby = zzeph7;
    }

    public static zzbsi zza(zzeph<zzdog> zzeph, zzeph<zzdnv> zzeph2, zzeph<zzbvb> zzeph3, zzeph<zzbvr> zzeph4, zzeph<zzdls> zzeph5, zzeph<zzbty> zzeph6, zzeph<zzbwv> zzeph7) {
        return new zzbsi(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7);
    }

    public final /* synthetic */ Object get() {
        return new zzbqn(this.zzfnn.get(), this.zzfrj.get(), this.zzfbx.get(), this.zzfdf.get(), this.zzftc.get(), this.zzftd.get(), this.zzfby.get());
    }
}
