package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdou implements zzeoy<zzdor> {
    private final zzeph<zzdst> zzezd;
    private final zzeph<zzdnv> zzfrj;
    private final zzeph<zzdnw> zzgez;
    private final zzeph<zzcsp> zzggy;

    private zzdou(zzeph<zzcsp> zzeph, zzeph<zzdst> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzdnw> zzeph4) {
        this.zzggy = zzeph;
        this.zzezd = zzeph2;
        this.zzfrj = zzeph3;
        this.zzgez = zzeph4;
    }

    public static zzdou zzi(zzeph<zzcsp> zzeph, zzeph<zzdst> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzdnw> zzeph4) {
        return new zzdou(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzdor(this.zzggy.get(), this.zzezd.get(), this.zzfrj.get(), this.zzgez.get());
    }
}
