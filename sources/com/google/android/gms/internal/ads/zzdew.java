package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdew implements zzeoy<zzdeu> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzdnt> zzgox;

    private zzdew(zzeph<zzdzb> zzeph, zzeph<zzdnt> zzeph2) {
        this.zzesr = zzeph;
        this.zzgox = zzeph2;
    }

    public static zzdew zzbb(zzeph<zzdzb> zzeph, zzeph<zzdnt> zzeph2) {
        return new zzdew(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdeu(this.zzesr.get(), this.zzgox.get());
    }
}
