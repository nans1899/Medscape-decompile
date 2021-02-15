package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdxu<InputT, OutputT> extends zzdxy<OutputT> {
    private static final Logger logger = Logger.getLogger(zzdxu.class.getName());
    /* access modifiers changed from: private */
    @NullableDecl
    public zzdwk<? extends zzdzc<? extends InputT>> zzhsi;
    private final boolean zzhsj;
    private final boolean zzhsk;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    enum zza {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED
    }

    zzdxu(zzdwk<? extends zzdzc<? extends InputT>> zzdwk, boolean z, boolean z2) {
        super(zzdwk.size());
        this.zzhsi = (zzdwk) zzdwd.checkNotNull(zzdwk);
        this.zzhsj = z;
        this.zzhsk = z2;
    }

    /* access modifiers changed from: package-private */
    public abstract void zzaxz();

    /* access modifiers changed from: package-private */
    public abstract void zzb(int i, @NullableDecl InputT inputt);

    /* access modifiers changed from: protected */
    public final void afterDone() {
        super.afterDone();
        zzdwk<? extends zzdzc<? extends InputT>> zzdwk = this.zzhsi;
        zza(zza.OUTPUT_FUTURE_DONE);
        if (isCancelled() && (zzdwk != null)) {
            boolean wasInterrupted = wasInterrupted();
            zzdxg zzdxg = (zzdxg) zzdwk.iterator();
            while (zzdxg.hasNext()) {
                ((Future) zzdxg.next()).cancel(wasInterrupted);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final String pendingToString() {
        zzdwk<? extends zzdzc<? extends InputT>> zzdwk = this.zzhsi;
        if (zzdwk == null) {
            return super.pendingToString();
        }
        String valueOf = String.valueOf(zzdwk);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
        sb.append("futures=");
        sb.append(valueOf);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zzaxy() {
        if (this.zzhsi.isEmpty()) {
            zzaxz();
        } else if (this.zzhsj) {
            int i = 0;
            zzdxg zzdxg = (zzdxg) this.zzhsi.iterator();
            while (zzdxg.hasNext()) {
                zzdzc zzdzc = (zzdzc) zzdxg.next();
                zzdzc.addListener(new zzdxx(this, zzdzc, i), zzdyj.INSTANCE);
                i++;
            }
        } else {
            zzdxw zzdxw = new zzdxw(this, this.zzhsk ? this.zzhsi : null);
            zzdxg zzdxg2 = (zzdxg) this.zzhsi.iterator();
            while (zzdxg2.hasNext()) {
                ((zzdzc) zzdxg2.next()).addListener(zzdxw, zzdyj.INSTANCE);
            }
        }
    }

    private final void zzk(Throwable th) {
        zzdwd.checkNotNull(th);
        if (this.zzhsj && !setException(th) && zza(zzaya(), th)) {
            zzl(th);
        } else if (th instanceof Error) {
            zzl(th);
        }
    }

    private static void zzl(Throwable th) {
        logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", net.media.android.bidder.base.models.internal.Logger.LOG, th instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
    }

    /* access modifiers changed from: package-private */
    public final void zzh(Set<Throwable> set) {
        zzdwd.checkNotNull(set);
        if (!isCancelled()) {
            zza(set, zzaxv());
        }
    }

    /* access modifiers changed from: private */
    public final void zza(int i, Future<? extends InputT> future) {
        try {
            zzb(i, zzdyq.zza(future));
        } catch (ExecutionException e) {
            zzk(e.getCause());
        } catch (Throwable th) {
            zzk(th);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(@NullableDecl zzdwk<? extends Future<? extends InputT>> zzdwk) {
        int zzayb = zzayb();
        int i = 0;
        if (!(zzayb >= 0)) {
            throw new IllegalStateException("Less than 0 remaining futures");
        } else if (zzayb == 0) {
            if (zzdwk != null) {
                zzdxg zzdxg = (zzdxg) zzdwk.iterator();
                while (zzdxg.hasNext()) {
                    Future future = (Future) zzdxg.next();
                    if (!future.isCancelled()) {
                        zza(i, future);
                    }
                    i++;
                }
            }
            zzayc();
            zzaxz();
            zza(zza.ALL_INPUT_FUTURES_PROCESSED);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zza zza2) {
        zzdwd.checkNotNull(zza2);
        this.zzhsi = null;
    }

    private static boolean zza(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }
}
