package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtd implements zzbuh, zzbuv, zzbyc, zzbzu {
    private final Executor executor;
    /* access modifiers changed from: private */
    public final zzbuu zzftk;
    private final zzdnv zzftl;
    private final ScheduledExecutorService zzftm;
    private zzdzj<Boolean> zzftn = zzdzj.zzayi();
    private ScheduledFuture<?> zzfto;

    public zzbtd(zzbuu zzbuu, zzdnv zzdnv, ScheduledExecutorService scheduledExecutorService, Executor executor2) {
        this.zzftk = zzbuu;
        this.zzftl = zzdnv;
        this.zzftm = scheduledExecutorService;
        this.executor = executor2;
    }

    public final void onAdClosed() {
    }

    public final void onAdLeftApplication() {
    }

    public final void onRewardedVideoCompleted() {
    }

    public final void onRewardedVideoStarted() {
    }

    public final void zzajl() {
    }

    public final void zzajm() {
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
    }

    public final void onAdOpened() {
        if (this.zzftl.zzhed == 0 || this.zzftl.zzhed == 1) {
            this.zzftk.onAdImpression();
        }
    }

    public final void zzajj() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqh)).booleanValue() && this.zzftl.zzhed == 2) {
            if (this.zzftl.zzhdp == 0) {
                this.zzftk.onAdImpression();
                return;
            }
            zzdyq.zza(this.zzftn, new zzbtf(this), this.executor);
            this.zzfto = this.zzftm.schedule(new zzbtg(this), (long) this.zzftl.zzhdp, TimeUnit.MILLISECONDS);
        }
    }

    public final synchronized void zzj(zzva zzva) {
        if (!this.zzftn.isDone()) {
            if (this.zzfto != null) {
                this.zzfto.cancel(true);
            }
            this.zzftn.setException(new Exception());
        }
    }

    public final synchronized void zzajk() {
        if (!this.zzftn.isDone()) {
            if (this.zzfto != null) {
                this.zzfto.cancel(true);
            }
            this.zzftn.set(true);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzajn() {
        synchronized (this) {
            if (!this.zzftn.isDone()) {
                this.zzftn.set(true);
            }
        }
    }
}
