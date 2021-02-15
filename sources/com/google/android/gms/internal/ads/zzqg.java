package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzqg {
    private final Handler handler;
    /* access modifiers changed from: private */
    public final zzqd zzbmy;

    public zzqg(Handler handler2, zzqd zzqd) {
        this.handler = zzqd != null ? (Handler) zzoz.checkNotNull(handler2) : null;
        this.zzbmy = zzqd;
    }

    public final void zzc(zzjl zzjl) {
        if (this.zzbmy != null) {
            this.handler.post(new zzqf(this, zzjl));
        }
    }

    public final void zzb(String str, long j, long j2) {
        if (this.zzbmy != null) {
            this.handler.post(new zzqi(this, str, j, j2));
        }
    }

    public final void zzc(zzho zzho) {
        if (this.zzbmy != null) {
            this.handler.post(new zzqh(this, zzho));
        }
    }

    public final void zzf(int i, long j) {
        if (this.zzbmy != null) {
            this.handler.post(new zzqk(this, i, j));
        }
    }

    public final void zzb(int i, int i2, int i3, float f) {
        if (this.zzbmy != null) {
            this.handler.post(new zzqj(this, i, i2, i3, f));
        }
    }

    public final void zzb(Surface surface) {
        if (this.zzbmy != null) {
            this.handler.post(new zzqm(this, surface));
        }
    }

    public final void zzd(zzjl zzjl) {
        if (this.zzbmy != null) {
            this.handler.post(new zzql(this, zzjl));
        }
    }
}
