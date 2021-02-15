package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdyh extends zzdyg<V> {
    private final /* synthetic */ zzdye zzhsz;
    private final Callable<V> zzhta;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdyh(zzdye zzdye, Callable<V> callable, Executor executor) {
        super(zzdye, executor);
        this.zzhsz = zzdye;
        this.zzhta = (Callable) zzdwd.checkNotNull(callable);
    }

    /* access modifiers changed from: package-private */
    public final V zzayd() throws Exception {
        this.zzhsy = false;
        return this.zzhta.call();
    }

    /* access modifiers changed from: package-private */
    public final void setValue(V v) {
        this.zzhsz.set(v);
    }

    /* access modifiers changed from: package-private */
    public final String zzaye() {
        return this.zzhta.toString();
    }
}
