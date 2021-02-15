package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzakg implements Runnable {
    private final zzakd zzdgu;
    private final zzahq zzdgv;
    private final Map zzdgw;

    zzakg(zzakd zzakd, zzahq zzahq, Map map) {
        this.zzdgu = zzakd;
        this.zzdgv = zzahq;
        this.zzdgw = map;
    }

    public final void run() {
        this.zzdgu.zza(this.zzdgv, this.zzdgw);
    }
}
