package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhx implements zzeoy<zzdhv> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzatj> zzetv;
    private final zzeph<String> zzfuc;

    public zzdhx(zzeph<zzatj> zzeph, zzeph<Context> zzeph2, zzeph<String> zzeph3, zzeph<zzdzb> zzeph4) {
        this.zzetv = zzeph;
        this.zzesu = zzeph2;
        this.zzfuc = zzeph3;
        this.zzesr = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzdhv(this.zzetv.get(), this.zzesu.get(), this.zzfuc.get(), this.zzesr.get());
    }
}
