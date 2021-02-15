package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcam implements zzeoy<zzcaj> {
    private final zzeph<zzdst> zzezd;
    private final zzeph<zzdnv> zzfno;

    private zzcam(zzeph<zzdnv> zzeph, zzeph<zzdst> zzeph2) {
        this.zzfno = zzeph;
        this.zzezd = zzeph2;
    }

    public static zzcam zzv(zzeph<zzdnv> zzeph, zzeph<zzdst> zzeph2) {
        return new zzcam(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcaj(this.zzfno.get(), this.zzezd.get());
    }
}
