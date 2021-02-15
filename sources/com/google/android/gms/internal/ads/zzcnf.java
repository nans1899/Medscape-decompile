package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcnf implements zzeoy<zzcng> {
    private final zzeph<Clock> zzfok;

    public zzcnf(zzeph<Clock> zzeph) {
        this.zzfok = zzeph;
    }

    public final /* synthetic */ Object get() {
        return new zzcng(this.zzfok.get());
    }
}
