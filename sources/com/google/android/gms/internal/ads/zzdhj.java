package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhj implements zzeoy<zzdhh> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;

    private zzdhj(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2) {
        this.zzesr = zzeph;
        this.zzesu = zzeph2;
    }

    public static zzdhj zzbh(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2) {
        return new zzdhj(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdhh(this.zzesr.get(), this.zzesu.get());
    }
}
