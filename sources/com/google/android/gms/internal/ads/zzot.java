package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzot {
    /* access modifiers changed from: private */
    public final ExecutorService zzbje;
    /* access modifiers changed from: private */
    public zzov<? extends zzoy> zzbjf;
    /* access modifiers changed from: private */
    public IOException zzbjg;

    public zzot(String str) {
        this.zzbje = zzpq.zzbi(str);
    }

    public final <T extends zzoy> long zza(T t, zzow<T> zzow, int i) {
        Looper myLooper = Looper.myLooper();
        zzoz.checkState(myLooper != null);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        new zzov(this, myLooper, t, zzow, i, elapsedRealtime).zzek(0);
        return elapsedRealtime;
    }

    public final boolean isLoading() {
        return this.zzbjf != null;
    }

    public final void zzis() {
        this.zzbjf.zzl(false);
    }

    public final void zza(Runnable runnable) {
        zzov<? extends zzoy> zzov = this.zzbjf;
        if (zzov != null) {
            zzov.zzl(true);
        }
        this.zzbje.execute(runnable);
        this.zzbje.shutdown();
    }

    public final void zzbj(int i) throws IOException {
        IOException iOException = this.zzbjg;
        if (iOException == null) {
            zzov<? extends zzoy> zzov = this.zzbjf;
            if (zzov != null) {
                zzov.zzbj(zzov.zzbjl);
                return;
            }
            return;
        }
        throw iOException;
    }
}
