package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbca implements Executor {
    private final Handler zzeev = new zzayo(Looper.getMainLooper());

    zzbca() {
    }

    public final void execute(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable.run();
            } catch (Throwable th) {
                zzp.zzkr();
                zzayu.zza(zzp.zzkv().getApplicationContext(), th);
                throw th;
            }
        } else {
            this.zzeev.post(runnable);
        }
    }
}
