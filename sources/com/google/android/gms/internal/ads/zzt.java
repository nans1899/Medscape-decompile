package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzt implements zzak {
    private final Executor zzag;

    public zzt(Handler handler) {
        this.zzag = new zzs(this, handler);
    }

    public final void zzb(zzaa<?> zzaa, zzaj<?> zzaj) {
        zza(zzaa, zzaj, (Runnable) null);
    }

    public final void zza(zzaa<?> zzaa, zzaj<?> zzaj, Runnable runnable) {
        zzaa.zzk();
        zzaa.zzc("post-response");
        this.zzag.execute(new zzv(zzaa, zzaj, runnable));
    }

    public final void zza(zzaa<?> zzaa, zzao zzao) {
        zzaa.zzc("post-error");
        this.zzag.execute(new zzv(zzaa, zzaj.zzd(zzao), (Runnable) null));
    }
}
