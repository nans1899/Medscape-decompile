package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdds implements zzeoy<zzddq> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<String> zzfow;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzckw> zzgwm;

    private zzdds(zzeph<zzdzb> zzeph, zzeph<zzckw> zzeph2, zzeph<zzdok> zzeph3, zzeph<String> zzeph4) {
        this.zzesr = zzeph;
        this.zzgwm = zzeph2;
        this.zzfqr = zzeph3;
        this.zzfow = zzeph4;
    }

    public static zzdds zzh(zzeph<zzdzb> zzeph, zzeph<zzckw> zzeph2, zzeph<zzdok> zzeph3, zzeph<String> zzeph4) {
        return new zzdds(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzddq(this.zzesr.get(), this.zzgwm.get(), this.zzfqr.get(), this.zzfow.get());
    }
}
