package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzs extends zzdyy<V> {
    private final Callable<V> zzhta;
    private final /* synthetic */ zzdzq zzhty;

    zzdzs(zzdzq zzdzq, Callable<V> callable) {
        this.zzhty = zzdzq;
        this.zzhta = (Callable) zzdwd.checkNotNull(callable);
    }

    /* access modifiers changed from: package-private */
    public final boolean isDone() {
        return this.zzhty.isDone();
    }

    /* access modifiers changed from: package-private */
    public final V zzayd() throws Exception {
        return this.zzhta.call();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(V v, Throwable th) {
        if (th == null) {
            this.zzhty.set(v);
        } else {
            this.zzhty.setException(th);
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzaye() {
        return this.zzhta.toString();
    }
}
