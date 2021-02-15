package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdp {
    private final long zzehf = TimeUnit.MILLISECONDS.toNanos(((Long) zzwm.zzpx().zzd(zzabb.zzcmp)).longValue());
    private long zzehg;
    private boolean zzehh = true;

    zzbdp() {
    }

    public final void zzzd() {
        this.zzehh = true;
    }

    public final void zza(SurfaceTexture surfaceTexture, zzbdc zzbdc) {
        if (zzbdc != null) {
            long timestamp = surfaceTexture.getTimestamp();
            if (this.zzehh || Math.abs(timestamp - this.zzehg) >= this.zzehf) {
                this.zzehh = false;
                this.zzehg = timestamp;
                zzayu.zzeba.post(new zzbdo(this, zzbdc));
            }
        }
    }
}
