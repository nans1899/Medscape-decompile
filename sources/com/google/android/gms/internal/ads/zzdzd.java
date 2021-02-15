package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzd implements Executor {
    boolean zzhtl = true;
    private final /* synthetic */ Executor zzhtm;
    private final /* synthetic */ zzdxq zzhtn;

    zzdzd(Executor executor, zzdxq zzdxq) {
        this.zzhtm = executor;
        this.zzhtn = zzdxq;
    }

    public final void execute(Runnable runnable) {
        try {
            this.zzhtm.execute(new zzdzg(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.zzhtl) {
                this.zzhtn.setException(e);
            }
        }
    }
}
