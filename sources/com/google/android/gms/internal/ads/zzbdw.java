package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbdw implements Runnable {
    private boolean zzbsu = false;
    private zzbdh zzehd;

    zzbdw(zzbdh zzbdh) {
        this.zzehd = zzbdh;
    }

    public final void run() {
        if (!this.zzbsu) {
            this.zzehd.zzzk();
            zzaab();
        }
    }

    public final void pause() {
        this.zzbsu = true;
        this.zzehd.zzzk();
    }

    public final void resume() {
        this.zzbsu = false;
        zzaab();
    }

    private final void zzaab() {
        zzayu.zzeba.removeCallbacks(this);
        zzayu.zzeba.postDelayed(this, 250);
    }
}
