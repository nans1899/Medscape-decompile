package com.google.android.gms.internal.ads;

import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzij {
    private final Handler handler;
    /* access modifiers changed from: private */
    public final zzig zzaje;

    public zzij(Handler handler2, zzig zzig) {
        this.handler = zzig != null ? (Handler) zzoz.checkNotNull(handler2) : null;
        this.zzaje = zzig;
    }

    public final void zzc(zzjl zzjl) {
        if (this.zzaje != null) {
            this.handler.post(new zzii(this, zzjl));
        }
    }

    public final void zzb(String str, long j, long j2) {
        if (this.zzaje != null) {
            this.handler.post(new zzil(this, str, j, j2));
        }
    }

    public final void zzc(zzho zzho) {
        if (this.zzaje != null) {
            this.handler.post(new zzik(this, zzho));
        }
    }

    public final void zzb(int i, long j, long j2) {
        if (this.zzaje != null) {
            this.handler.post(new zzin(this, i, j, j2));
        }
    }

    public final void zzd(zzjl zzjl) {
        if (this.zzaje != null) {
            this.handler.post(new zzim(this, zzjl));
        }
    }

    public final void zzy(int i) {
        if (this.zzaje != null) {
            this.handler.post(new zzip(this, i));
        }
    }
}
