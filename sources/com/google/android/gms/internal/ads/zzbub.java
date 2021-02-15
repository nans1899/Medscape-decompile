package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbub implements zzeoy<zzbty> {
    private final zzeph<zzctd> zzewv;
    private final zzeph<zzdnv> zzfrj;
    private final zzeph<String> zzfun;

    private zzbub(zzeph<zzdnv> zzeph, zzeph<String> zzeph2, zzeph<zzctd> zzeph3) {
        this.zzfrj = zzeph;
        this.zzfun = zzeph2;
        this.zzewv = zzeph3;
    }

    public static zzbub zzi(zzeph<zzdnv> zzeph, zzeph<String> zzeph2, zzeph<zzctd> zzeph3) {
        return new zzbub(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzbty(this.zzfrj.get(), this.zzfun.get(), this.zzewv.get());
    }
}
