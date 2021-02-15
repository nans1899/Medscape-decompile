package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdxq;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.description.method.MethodDescription;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdxy<OutputT> extends zzdxq.zzh<OutputT> {
    private static final Logger zzhrt;
    private static final zza zzhsp;
    private volatile int remaining;
    /* access modifiers changed from: private */
    public volatile Set<Throwable> seenExceptions = null;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static abstract class zza {
        private zza() {
        }

        /* access modifiers changed from: package-private */
        public abstract void zza(zzdxy zzdxy, Set<Throwable> set, Set<Throwable> set2);

        /* access modifiers changed from: package-private */
        public abstract int zzc(zzdxy zzdxy);
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzb extends zza {
        private zzb() {
            super();
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzdxy zzdxy, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (zzdxy) {
                if (zzdxy.seenExceptions == null) {
                    Set unused = zzdxy.seenExceptions = set2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final int zzc(zzdxy zzdxy) {
            int zzb;
            synchronized (zzdxy) {
                zzb = zzdxy.zzb(zzdxy);
            }
            return zzb;
        }
    }

    zzdxy(int i) {
        this.remaining = i;
    }

    /* access modifiers changed from: package-private */
    public abstract void zzh(Set<Throwable> set);

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzc extends zza {
        private final AtomicReferenceFieldUpdater<zzdxy, Set<Throwable>> zzhst;
        private final AtomicIntegerFieldUpdater<zzdxy> zzhsu;

        zzc(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.zzhst = atomicReferenceFieldUpdater;
            this.zzhsu = atomicIntegerFieldUpdater;
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzdxy zzdxy, Set<Throwable> set, Set<Throwable> set2) {
            this.zzhst.compareAndSet(zzdxy, (Object) null, set2);
        }

        /* access modifiers changed from: package-private */
        public final int zzc(zzdxy zzdxy) {
            return this.zzhsu.decrementAndGet(zzdxy);
        }
    }

    /* access modifiers changed from: package-private */
    public final Set<Throwable> zzaya() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        zzh(newSetFromMap);
        zzhsp.zza(this, (Set<Throwable>) null, newSetFromMap);
        return this.seenExceptions;
    }

    /* access modifiers changed from: package-private */
    public final int zzayb() {
        return zzhsp.zzc(this);
    }

    /* access modifiers changed from: package-private */
    public final void zzayc() {
        this.seenExceptions = null;
    }

    static /* synthetic */ int zzb(zzdxy zzdxy) {
        int i = zzdxy.remaining - 1;
        zzdxy.remaining = i;
        return i;
    }

    static {
        Throwable th;
        zza zza2;
        Class<zzdxy> cls = zzdxy.class;
        zzhrt = Logger.getLogger(cls.getName());
        try {
            zza2 = new zzc(AtomicReferenceFieldUpdater.newUpdater(cls, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(cls, "remaining"));
            th = null;
        } catch (Throwable th2) {
            zza2 = new zzb();
            th = th2;
        }
        zzhsp = zza2;
        if (th != null) {
            zzhrt.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME, "SafeAtomicHelper is broken!", th);
        }
    }
}
