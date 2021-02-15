package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcnb implements zzeoy<zzcmz> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbu> zzezd;
    private final zzeph<zzbbx> zzfmh;

    public zzcnb(zzeph<Executor> zzeph, zzeph<zzbbu> zzeph2, zzeph<Context> zzeph3, zzeph<zzbbx> zzeph4) {
        this.zzesr = zzeph;
        this.zzezd = zzeph2;
        this.zzesu = zzeph3;
        this.zzfmh = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzcmz(this.zzesr.get(), this.zzezd.get(), this.zzesu.get(), this.zzfmh.get());
    }
}
