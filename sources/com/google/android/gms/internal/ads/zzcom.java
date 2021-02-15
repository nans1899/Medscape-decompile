package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcom implements zzeoy<zzcoi> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzdzb> zzgjg;
    private final zzeph<zzcpt> zzgjh;
    private final zzeph<zzcqv> zzgji;

    private zzcom(zzeph<zzdzb> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzcpt> zzeph3, zzeph<zzcqv> zzeph4) {
        this.zzgjg = zzeph;
        this.zzesr = zzeph2;
        this.zzgjh = zzeph3;
        this.zzgji = zzeph4;
    }

    public static zzcom zzd(zzeph<zzdzb> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzcpt> zzeph3, zzeph<zzcqv> zzeph4) {
        return new zzcom(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzcoi(this.zzgjg.get(), this.zzesr.get(), this.zzgjh.get(), zzeov.zzat(this.zzgji));
    }
}
