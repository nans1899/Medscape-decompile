package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzdyp<V> extends zzdym<V> implements zzdzc<V> {
    protected zzdyp() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzayg */
    public abstract zzdzc<? extends V> zzayf();

    public void addListener(Runnable runnable, Executor executor) {
        zzayf().addListener(runnable, executor);
    }
}
