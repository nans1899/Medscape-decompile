package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbus implements zzeoy<zzbuk> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbuo> zzfdv;
    private final zzeph<Set<zzcab<zzbul>>> zzfqq;

    private zzbus(zzeph<zzbuo> zzeph, zzeph<Set<zzcab<zzbul>>> zzeph2, zzeph<Executor> zzeph3) {
        this.zzfdv = zzeph;
        this.zzfqq = zzeph2;
        this.zzesr = zzeph3;
    }

    public static zzbus zzj(zzeph<zzbuo> zzeph, zzeph<Set<zzcab<zzbul>>> zzeph2, zzeph<Executor> zzeph3) {
        return new zzbus(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzbuk(this.zzfdv.get(), this.zzfqq.get(), this.zzesr.get());
    }
}
