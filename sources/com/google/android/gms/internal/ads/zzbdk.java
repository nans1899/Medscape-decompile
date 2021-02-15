package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbdk implements Runnable {
    private final /* synthetic */ zzbdh zzegy;
    private final /* synthetic */ boolean zzehb;

    zzbdk(zzbdh zzbdh, boolean z) {
        this.zzegy = zzbdh;
        this.zzehb = z;
    }

    public final void run() {
        this.zzegy.zzd("windowVisibilityChanged", "isVisible", String.valueOf(this.zzehb));
    }
}
