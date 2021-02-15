package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcrv implements Callable {
    private final zzcru zzglv;

    private zzcrv(zzcru zzcru) {
        this.zzglv = zzcru;
    }

    static Callable zza(zzcru zzcru) {
        return new zzcrv(zzcru);
    }

    public final Object call() {
        return this.zzglv.getWritableDatabase();
    }
}
