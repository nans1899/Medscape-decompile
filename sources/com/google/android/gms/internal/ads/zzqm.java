package com.google.android.gms.internal.ads;

import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqm implements Runnable {
    private final /* synthetic */ zzqg zzbmx;
    private final /* synthetic */ Surface zzbnf;

    zzqm(zzqg zzqg, Surface surface) {
        this.zzbmx = zzqg;
        this.zzbnf = surface;
    }

    public final void run() {
        this.zzbmx.zzbmy.zza(this.zzbnf);
    }
}
