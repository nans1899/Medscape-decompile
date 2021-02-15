package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcla implements zzeoy<zzckw> {
    private final zzeph<Executor> zzesr;

    public zzcla(zzeph<Executor> zzeph) {
        this.zzesr = zzeph;
    }

    public final /* synthetic */ Object get() {
        return new zzckw(this.zzesr.get());
    }
}
