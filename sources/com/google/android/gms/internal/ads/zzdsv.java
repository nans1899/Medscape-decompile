package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdsv implements zzeoy<zzdst> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbbu> zzetm;

    private zzdsv(zzeph<Executor> zzeph, zzeph<zzbbu> zzeph2) {
        this.zzesr = zzeph;
        this.zzetm = zzeph2;
    }

    public static zzdsv zzbj(zzeph<Executor> zzeph, zzeph<zzbbu> zzeph2) {
        return new zzdsv(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdst(this.zzesr.get(), this.zzetm.get());
    }
}
