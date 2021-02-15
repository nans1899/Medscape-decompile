package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdys<V> implements Runnable {
    private final Future<V> zzhte;
    private final zzdyr<? super V> zzhtf;

    zzdys(Future<V> future, zzdyr<? super V> zzdyr) {
        this.zzhte = future;
        this.zzhtf = zzdyr;
    }

    public final void run() {
        Throwable zza;
        Future<V> future = this.zzhte;
        if (!(future instanceof zzdzt) || (zza = zzdzw.zza((zzdzt) future)) == null) {
            try {
                this.zzhtf.onSuccess(zzdyq.zza(this.zzhte));
            } catch (ExecutionException e) {
                this.zzhtf.zzb(e.getCause());
            } catch (Error | RuntimeException e2) {
                this.zzhtf.zzb(e2);
            }
        } else {
            this.zzhtf.zzb(zza);
        }
    }

    public final String toString() {
        return zzdvt.zzx(this).zzy(this.zzhtf).toString();
    }
}
