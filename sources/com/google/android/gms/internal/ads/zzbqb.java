package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqb implements zzeoy<zzbpw> {
    private final zzeph<zzbqn> zzfeb;
    private final zzeph<Runnable> zzfkd;
    private final zzeph<zzafy> zzfop;
    private final zzeph<Executor> zzfqk;

    public zzbqb(zzeph<zzbqn> zzeph, zzeph<zzafy> zzeph2, zzeph<Runnable> zzeph3, zzeph<Executor> zzeph4) {
        this.zzfeb = zzeph;
        this.zzfop = zzeph2;
        this.zzfkd = zzeph3;
        this.zzfqk = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzbpw(this.zzfeb.get(), this.zzfop.get(), this.zzfkd.get(), this.zzfqk.get());
    }
}
