package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddh implements zzeoy<zzddf> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;

    private zzddh(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2) {
        this.zzesr = zzeph;
        this.zzesu = zzeph2;
    }

    public static zzddh zzay(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2) {
        return new zzddh(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzddf(this.zzesr.get(), this.zzesu.get());
    }
}
