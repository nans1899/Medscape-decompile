package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvf implements zzeoy<zzcvd> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzcce> zzgof;

    public zzcvf(zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzcce> zzeph3, zzeph<Executor> zzeph4) {
        this.zzesu = zzeph;
        this.zzfov = zzeph2;
        this.zzgof = zzeph3;
        this.zzfqk = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzcvd(this.zzesu.get(), this.zzfov.get(), this.zzgof.get(), this.zzfqk.get());
    }
}
