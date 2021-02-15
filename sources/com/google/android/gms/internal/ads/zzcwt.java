package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwt implements zzeoy<zzcwr> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzcjj> zzgof;

    public zzcwt(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzcjj> zzeph3) {
        this.zzesu = zzeph;
        this.zzfqk = zzeph2;
        this.zzgof = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzcwr(this.zzesu.get(), this.zzfqk.get(), this.zzgof.get());
    }
}
