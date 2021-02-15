package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdrx implements Callable {
    private final zzdrs zzhke;

    zzdrx(zzdrs zzdrs) {
        this.zzhke = zzdrs;
    }

    public final Object call() {
        this.zzhke.run();
        return null;
    }
}
