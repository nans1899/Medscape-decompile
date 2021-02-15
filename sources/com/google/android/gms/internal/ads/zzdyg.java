package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdyg<T> extends zzdyy<T> {
    private final Executor zzhsx;
    boolean zzhsy = true;
    private final /* synthetic */ zzdye zzhsz;

    zzdyg(zzdye zzdye, Executor executor) {
        this.zzhsz = zzdye;
        this.zzhsx = (Executor) zzdwd.checkNotNull(executor);
    }

    /* access modifiers changed from: package-private */
    public abstract void setValue(T t);

    /* access modifiers changed from: package-private */
    public final boolean isDone() {
        return this.zzhsz.isDone();
    }

    /* access modifiers changed from: package-private */
    public final void execute() {
        try {
            this.zzhsx.execute(this);
        } catch (RejectedExecutionException e) {
            if (this.zzhsy) {
                this.zzhsz.setException(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(T t, Throwable th) {
        zzdyg unused = this.zzhsz.zzhsw = null;
        if (th == null) {
            setValue(t);
        } else if (th instanceof ExecutionException) {
            this.zzhsz.setException(th.getCause());
        } else if (th instanceof CancellationException) {
            this.zzhsz.cancel(false);
        } else {
            this.zzhsz.setException(th);
        }
    }
}
