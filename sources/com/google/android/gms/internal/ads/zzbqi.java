package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqi implements zzeoy<zzbqf> {
    private final zzeph<zzdnv> zzfno;
    private final zzeph<zzbuu> zzfrl;
    private final zzeph<zzbvw> zzfrm;

    private zzbqi(zzeph<zzdnv> zzeph, zzeph<zzbuu> zzeph2, zzeph<zzbvw> zzeph3) {
        this.zzfno = zzeph;
        this.zzfrl = zzeph2;
        this.zzfrm = zzeph3;
    }

    public static zzbqi zzf(zzeph<zzdnv> zzeph, zzeph<zzbuu> zzeph2, zzeph<zzbvw> zzeph3) {
        return new zzbqi(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzbqf(this.zzfno.get(), this.zzfrl.get(), this.zzfrm.get());
    }
}
