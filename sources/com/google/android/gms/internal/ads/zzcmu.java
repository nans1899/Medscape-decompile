package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmu {
    /* access modifiers changed from: private */
    public final Executor executor;
    /* access modifiers changed from: private */
    public final zzcmz zzghe;
    /* access modifiers changed from: private */
    public final Map<String, String> zzghi;

    public zzcmu(zzcmz zzcmz, Executor executor2) {
        this.zzghe = zzcmz;
        this.zzghi = zzcmz.zzapg();
        this.executor = executor2;
    }

    public final zzcmt zzapf() {
        return new zzcmt(this).zzapb();
    }
}
