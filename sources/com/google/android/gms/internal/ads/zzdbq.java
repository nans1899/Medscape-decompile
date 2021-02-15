package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbq implements zzeoy<zzdbo> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<ViewGroup> zzgpw;
    private final zzeph<zzdzb> zzgvb;

    public zzdbq(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2, zzeph<zzdok> zzeph3, zzeph<ViewGroup> zzeph4) {
        this.zzgvb = zzeph;
        this.zzesu = zzeph2;
        this.zzfqr = zzeph3;
        this.zzgpw = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzdbo(this.zzgvb.get(), this.zzesu.get(), this.zzfqr.get(), this.zzgpw.get());
    }
}
