package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfj implements zzeoy<zzcfh> {
    private final zzeph<zzcil> zzffj;
    private final zzeph<Clock> zzfok;

    public zzcfj(zzeph<zzcil> zzeph, zzeph<Clock> zzeph2) {
        this.zzffj = zzeph;
        this.zzfok = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return new zzcfh(this.zzffj.get(), this.zzfok.get());
    }
}
