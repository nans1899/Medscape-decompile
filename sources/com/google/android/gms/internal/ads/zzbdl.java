package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbdl implements Runnable {
    private final /* synthetic */ zzbdh zzegy;

    zzbdl(zzbdh zzbdh) {
        this.zzegy = zzbdh;
    }

    public final void run() {
        this.zzegy.zzd("surfaceDestroyed", new String[0]);
    }
}
