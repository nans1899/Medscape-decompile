package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzm;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzft<V> extends FutureTask<V> implements Comparable<zzft<V>> {
    final boolean zza;
    private final long zzb;
    private final String zzc;
    private final /* synthetic */ zzfo zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzft(zzfo zzfo, Callable<V> callable, boolean z, String str) {
        super(zzm.zza().zza(callable));
        this.zzd = zzfo;
        Preconditions.checkNotNull(str);
        long andIncrement = zzfo.zzj.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzfo.zzq().zze().zza("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzft(zzfo zzfo, Runnable runnable, boolean z, String str) {
        super(zzm.zza().zza(runnable), (Object) null);
        this.zzd = zzfo;
        Preconditions.checkNotNull(str);
        long andIncrement = zzfo.zzj.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzfo.zzq().zze().zza("Tasks index overflow");
        }
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zzd.zzq().zze().zza(this.zzc, th);
        if (th instanceof zzfr) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzft zzft = (zzft) obj;
        boolean z = this.zza;
        if (z != zzft.zza) {
            return z ? -1 : 1;
        }
        long j = this.zzb;
        long j2 = zzft.zzb;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzd.zzq().zzf().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
        return 0;
    }
}
