package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbdj implements Runnable {
    private final zzbdh zzegz;
    private final boolean zzeha;

    zzbdj(zzbdh zzbdh, boolean z) {
        this.zzegz = zzbdh;
        this.zzeha = z;
    }

    public final void run() {
        this.zzegz.zzau(this.zzeha);
    }
}
