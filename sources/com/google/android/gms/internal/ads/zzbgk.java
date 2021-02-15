package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbgk implements Runnable {
    private final String zzdgt;

    zzbgk(String str) {
        this.zzdgt = str;
    }

    public final void run() {
        zzp.zzkv().zzwn().zzcv(this.zzdgt.substring(1));
    }
}
