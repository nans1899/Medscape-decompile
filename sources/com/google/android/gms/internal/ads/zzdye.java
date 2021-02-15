package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdxu;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdye<V> extends zzdxu<Object, V> {
    /* access modifiers changed from: private */
    public zzdyg<?> zzhsw;

    zzdye(zzdwk<? extends zzdzc<?>> zzdwk, boolean z, Executor executor, Callable<V> callable) {
        super(zzdwk, z, false);
        this.zzhsw = new zzdyh(this, callable, executor);
        zzaxy();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i, @NullableDecl Object obj) {
    }

    /* access modifiers changed from: package-private */
    public final void zzaxz() {
        zzdyg<?> zzdyg = this.zzhsw;
        if (zzdyg != null) {
            zzdyg.execute();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdxu.zza zza) {
        super.zza(zza);
        if (zza == zzdxu.zza.OUTPUT_FUTURE_DONE) {
            this.zzhsw = null;
        }
    }

    /* access modifiers changed from: protected */
    public final void interruptTask() {
        zzdyg<?> zzdyg = this.zzhsw;
        if (zzdyg != null) {
            zzdyg.interruptTask();
        }
    }
}
