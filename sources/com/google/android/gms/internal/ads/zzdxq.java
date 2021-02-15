package com.google.android.gms.internal.ads;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.description.method.MethodDescription;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzdxq<V> extends zzdzt implements zzdzc<V> {
    /* access modifiers changed from: private */
    public static final boolean GENERATE_CANCELLATION_CAUSES;
    private static final Object NULL = new Object();
    private static final Logger zzhrt;
    /* access modifiers changed from: private */
    public static final zzb zzhru;
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile zze listeners;
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile Object value;
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile zzk waiters;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static abstract class zzb {
        private zzb() {
        }

        /* access modifiers changed from: package-private */
        public abstract void zza(zzk zzk, zzk zzk2);

        /* access modifiers changed from: package-private */
        public abstract void zza(zzk zzk, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean zza(zzdxq<?> zzdxq, zze zze, zze zze2);

        /* access modifiers changed from: package-private */
        public abstract boolean zza(zzdxq<?> zzdxq, zzk zzk, zzk zzk2);

        /* access modifiers changed from: package-private */
        public abstract boolean zza(zzdxq<?> zzdxq, Object obj, Object obj2);
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzc {
        static final zzc zzhrx = new zzc(new Throwable("Failure occurred while trying to finish a future.") {
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        zzc(Throwable th) {
            this.exception = (Throwable) zzdwd.checkNotNull(th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zze {
        static final zze zzhry = new zze((Runnable) null, (Executor) null);
        final Executor executor;
        @NullableDecl
        zze next;
        final Runnable task;

        zze(Runnable runnable, Executor executor2) {
            this.task = runnable;
            this.executor = executor2;
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzf extends zzb {
        private zzf() {
            super();
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzk zzk, Thread thread) {
            zzk.thread = thread;
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzk zzk, zzk zzk2) {
            zzk.next = zzk2;
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, zzk zzk, zzk zzk2) {
            synchronized (zzdxq) {
                if (zzdxq.waiters != zzk) {
                    return false;
                }
                zzk unused = zzdxq.waiters = zzk2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, zze zze, zze zze2) {
            synchronized (zzdxq) {
                if (zzdxq.listeners != zze) {
                    return false;
                }
                zze unused = zzdxq.listeners = zze2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, Object obj, Object obj2) {
            synchronized (zzdxq) {
                if (zzdxq.value != obj) {
                    return false;
                }
                Object unused = zzdxq.value = obj2;
                return true;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static abstract class zzh<V> extends zzdxq<V> implements zzi<V> {
        zzh() {
        }

        public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return zzdxq.super.get(j, timeUnit);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    interface zzi<V> extends zzdzc<V> {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzj extends zzb {
        static final Unsafe zzhsa;
        static final long zzhsb;
        static final long zzhsc;
        static final long zzhsd;
        static final long zzhse;
        static final long zzhsf;

        private zzj() {
            super();
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzk zzk, Thread thread) {
            zzhsa.putObject(zzk, zzhse, thread);
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzk zzk, zzk zzk2) {
            zzhsa.putObject(zzk, zzhsf, zzk2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, zzk zzk, zzk zzk2) {
            return zzhsa.compareAndSwapObject(zzdxq, zzhsc, zzk, zzk2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, zze zze, zze zze2) {
            return zzhsa.compareAndSwapObject(zzdxq, zzhsb, zze, zze2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, Object obj, Object obj2) {
            return zzhsa.compareAndSwapObject(zzdxq, zzhsd, obj, obj2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x005f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x006b, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
            r0 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.android.gms.internal.ads.zzdxq.zzj.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
        static {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                goto L_0x0010
            L_0x0005:
                com.google.android.gms.internal.ads.zzdxq$zzj$1 r0 = new com.google.android.gms.internal.ads.zzdxq$zzj$1     // Catch:{ PrivilegedActionException -> 0x005f }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x005f }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x005f }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x005f }
            L_0x0010:
                java.lang.Class<com.google.android.gms.internal.ads.zzdxq> r1 = com.google.android.gms.internal.ads.zzdxq.class
                java.lang.String r2 = "waiters"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ Exception -> 0x0055 }
                zzhsc = r2     // Catch:{ Exception -> 0x0055 }
                java.lang.String r2 = "listeners"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ Exception -> 0x0055 }
                zzhsb = r2     // Catch:{ Exception -> 0x0055 }
                java.lang.String r2 = "value"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ Exception -> 0x0055 }
                zzhsd = r1     // Catch:{ Exception -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.ads.zzdxq$zzk> r1 = com.google.android.gms.internal.ads.zzdxq.zzk.class
                java.lang.String r2 = "thread"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ Exception -> 0x0055 }
                zzhse = r1     // Catch:{ Exception -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.ads.zzdxq$zzk> r1 = com.google.android.gms.internal.ads.zzdxq.zzk.class
                java.lang.String r2 = "next"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ Exception -> 0x0055 }
                zzhsf = r1     // Catch:{ Exception -> 0x0055 }
                zzhsa = r0     // Catch:{ Exception -> 0x0055 }
                return
            L_0x0055:
                r0 = move-exception
                com.google.android.gms.internal.ads.zzdwh.zzi(r0)
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                r1.<init>(r0)
                throw r1
            L_0x005f:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.Throwable r0 = r0.getCause()
                java.lang.String r2 = "Could not initialize intrinsics"
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdxq.zzj.<clinit>():void");
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzk {
        static final zzk zzhsg = new zzk(false);
        @NullableDecl
        volatile zzk next;
        @NullableDecl
        volatile Thread thread;

        private zzk(boolean z) {
        }

        zzk() {
            zzdxq.zzhru.zza(this, Thread.currentThread());
        }

        /* access modifiers changed from: package-private */
        public final void zzb(zzk zzk) {
            zzdxq.zzhru.zza(this, zzk);
        }
    }

    private final void zza(zzk zzk2) {
        zzk2.thread = null;
        while (true) {
            zzk zzk3 = this.waiters;
            if (zzk3 != zzk.zzhsg) {
                zzk zzk4 = null;
                while (zzk3 != null) {
                    zzk zzk5 = zzk3.next;
                    if (zzk3.thread != null) {
                        zzk4 = zzk3;
                    } else if (zzk4 != null) {
                        zzk4.next = zzk5;
                        if (zzk4.thread == null) {
                        }
                    } else if (zzhru.zza((zzdxq<?>) this, zzk3, zzk5)) {
                    }
                    zzk3 = zzk5;
                }
                return;
            }
            return;
        }
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
    }

    /* access modifiers changed from: protected */
    public void interruptTask() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zza {
        static final zza zzhrv;
        static final zza zzhrw;
        @NullableDecl
        final Throwable cause;
        final boolean wasInterrupted;

        zza(boolean z, @NullableDecl Throwable th) {
            this.wasInterrupted = z;
            this.cause = th;
        }

        static {
            if (zzdxq.GENERATE_CANCELLATION_CAUSES) {
                zzhrw = null;
                zzhrv = null;
                return;
            }
            zzhrw = new zza(false, (Throwable) null);
            zzhrv = new zza(true, (Throwable) null);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzg<V> implements Runnable {
        final zzdzc<? extends V> future;
        final zzdxq<V> zzhrz;

        zzg(zzdxq<V> zzdxq, zzdzc<? extends V> zzdzc) {
            this.zzhrz = zzdxq;
            this.future = zzdzc;
        }

        public final void run() {
            if (this.zzhrz.value == this) {
                if (zzdxq.zzhru.zza((zzdxq<?>) this.zzhrz, (Object) this, zzdxq.getFutureValue(this.future))) {
                    zzdxq.zza((zzdxq<?>) this.zzhrz);
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static final class zzd extends zzb {
        final AtomicReferenceFieldUpdater<zzdxq, zze> listenersUpdater;
        final AtomicReferenceFieldUpdater<zzdxq, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<zzk, zzk> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<zzk, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<zzdxq, zzk> waitersUpdater;

        zzd(AtomicReferenceFieldUpdater<zzk, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<zzk, zzk> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<zzdxq, zzk> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<zzdxq, zze> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<zzdxq, Object> atomicReferenceFieldUpdater5) {
            super();
            this.waiterThreadUpdater = atomicReferenceFieldUpdater;
            this.waiterNextUpdater = atomicReferenceFieldUpdater2;
            this.waitersUpdater = atomicReferenceFieldUpdater3;
            this.listenersUpdater = atomicReferenceFieldUpdater4;
            this.valueUpdater = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzk zzk, Thread thread) {
            this.waiterThreadUpdater.lazySet(zzk, thread);
        }

        /* access modifiers changed from: package-private */
        public final void zza(zzk zzk, zzk zzk2) {
            this.waiterNextUpdater.lazySet(zzk, zzk2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, zzk zzk, zzk zzk2) {
            return this.waitersUpdater.compareAndSet(zzdxq, zzk, zzk2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, zze zze, zze zze2) {
            return this.listenersUpdater.compareAndSet(zzdxq, zze, zze2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zza(zzdxq<?> zzdxq, Object obj, Object obj2) {
            return this.valueUpdater.compareAndSet(zzdxq, obj, obj2);
        }
    }

    protected zzdxq() {
    }

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            if ((obj != null) && (!(obj instanceof zzg))) {
                return zzae(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                zzk zzk2 = this.waiters;
                if (zzk2 != zzk.zzhsg) {
                    zzk zzk3 = new zzk();
                    do {
                        zzk3.zzb(zzk2);
                        if (zzhru.zza((zzdxq<?>) this, zzk2, zzk3)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof zzg))) {
                                        return zzae(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    zza(zzk3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zza(zzk3);
                        } else {
                            zzk2 = this.waiters;
                        }
                    } while (zzk2 != zzk.zzhsg);
                }
                return zzae(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if ((obj3 != null) && (!(obj3 instanceof zzg))) {
                    return zzae(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zzdxq = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String lowerCase2 = timeUnit.toString().toLowerCase(Locale.ROOT);
            StringBuilder sb = new StringBuilder(String.valueOf(lowerCase2).length() + 28);
            sb.append("Waited ");
            sb.append(j2);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(lowerCase2);
            String sb2 = sb.toString();
            if (nanos + 1000 < 0) {
                String concat = String.valueOf(sb2).concat(" (plus ");
                long j3 = -nanos;
                long convert = timeUnit2.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit2.toNanos(convert);
                int i = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                boolean z = i == 0 || nanos2 > 1000;
                if (i > 0) {
                    String valueOf = String.valueOf(concat);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(lowerCase).length());
                    sb3.append(valueOf);
                    sb3.append(convert);
                    sb3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    sb3.append(lowerCase);
                    String sb4 = sb3.toString();
                    if (z) {
                        sb4 = String.valueOf(sb4).concat(",");
                    }
                    concat = String.valueOf(sb4).concat(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                if (z) {
                    String valueOf2 = String.valueOf(concat);
                    StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf2).length() + 33);
                    sb5.append(valueOf2);
                    sb5.append(nanos2);
                    sb5.append(" nanoseconds ");
                    concat = sb5.toString();
                }
                sb2 = String.valueOf(concat).concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(String.valueOf(sb2).concat(" but future completed as timeout expired"));
            }
            StringBuilder sb6 = new StringBuilder(String.valueOf(sb2).length() + 5 + String.valueOf(zzdxq).length());
            sb6.append(sb2);
            sb6.append(" for ");
            sb6.append(zzdxq);
            throw new TimeoutException(sb6.toString());
        }
        throw new InterruptedException();
    }

    public V get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzg))) {
                return zzae(obj2);
            }
            zzk zzk2 = this.waiters;
            if (zzk2 != zzk.zzhsg) {
                zzk zzk3 = new zzk();
                do {
                    zzk3.zzb(zzk2);
                    if (zzhru.zza((zzdxq<?>) this, zzk2, zzk3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                zza(zzk3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof zzg))));
                        return zzae(obj);
                    }
                    zzk2 = this.waiters;
                } while (zzk2 != zzk.zzhsg);
            }
            return zzae(this.value);
        }
        throw new InterruptedException();
    }

    private static V zzae(Object obj) throws ExecutionException {
        if (obj instanceof zza) {
            Throwable th = ((zza) obj).cause;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).exception);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof zzg)) & (obj != null);
    }

    public boolean isCancelled() {
        return this.value instanceof zza;
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.internal.ads.zzdzc<? extends V>, com.google.android.gms.internal.ads.zzdzc] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.value
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0008
            r3 = 1
            goto L_0x0009
        L_0x0008:
            r3 = 0
        L_0x0009:
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzdxq.zzg
            r3 = r3 | r4
            if (r3 == 0) goto L_0x0061
            boolean r3 = GENERATE_CANCELLATION_CAUSES
            if (r3 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.zzdxq$zza r3 = new com.google.android.gms.internal.ads.zzdxq$zza
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r8, r4)
            goto L_0x0026
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzdxq$zza r3 = com.google.android.gms.internal.ads.zzdxq.zza.zzhrv
            goto L_0x0026
        L_0x0024:
            com.google.android.gms.internal.ads.zzdxq$zza r3 = com.google.android.gms.internal.ads.zzdxq.zza.zzhrw
        L_0x0026:
            r5 = 0
            r4 = r7
        L_0x0028:
            com.google.android.gms.internal.ads.zzdxq$zzb r6 = zzhru
            boolean r6 = r6.zza((com.google.android.gms.internal.ads.zzdxq<?>) r4, (java.lang.Object) r0, (java.lang.Object) r3)
            if (r6 == 0) goto L_0x0059
            if (r8 == 0) goto L_0x0035
            r4.interruptTask()
        L_0x0035:
            zza((com.google.android.gms.internal.ads.zzdxq<?>) r4)
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzdxq.zzg
            if (r4 == 0) goto L_0x0062
            com.google.android.gms.internal.ads.zzdxq$zzg r0 = (com.google.android.gms.internal.ads.zzdxq.zzg) r0
            com.google.android.gms.internal.ads.zzdzc<? extends V> r0 = r0.future
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzdxq.zzi
            if (r4 == 0) goto L_0x0055
            r4 = r0
            com.google.android.gms.internal.ads.zzdxq r4 = (com.google.android.gms.internal.ads.zzdxq) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L_0x004d
            r5 = 1
            goto L_0x004e
        L_0x004d:
            r5 = 0
        L_0x004e:
            boolean r6 = r0 instanceof com.google.android.gms.internal.ads.zzdxq.zzg
            r5 = r5 | r6
            if (r5 == 0) goto L_0x0062
            r5 = 1
            goto L_0x0028
        L_0x0055:
            r0.cancel(r8)
            goto L_0x0062
        L_0x0059:
            java.lang.Object r0 = r4.value
            boolean r6 = r0 instanceof com.google.android.gms.internal.ads.zzdxq.zzg
            if (r6 != 0) goto L_0x0028
            r1 = r5
            goto L_0x0062
        L_0x0061:
            r1 = 0
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdxq.cancel(boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof zza) && ((zza) obj).wasInterrupted;
    }

    public void addListener(Runnable runnable, Executor executor) {
        zze zze2;
        zzdwd.checkNotNull(runnable, "Runnable was null.");
        zzdwd.checkNotNull(executor, "Executor was null.");
        if (isDone() || (zze2 = this.listeners) == zze.zzhry) {
            zza(runnable, executor);
        }
        zze zze3 = new zze(runnable, executor);
        do {
            zze3.next = zze2;
            if (!zzhru.zza((zzdxq<?>) this, zze2, zze3)) {
                zze2 = this.listeners;
            } else {
                return;
            }
        } while (zze2 != zze.zzhry);
        zza(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public boolean set(@NullableDecl V v) {
        if (v == null) {
            v = NULL;
        }
        if (!zzhru.zza((zzdxq<?>) this, (Object) null, (Object) v)) {
            return false;
        }
        zza((zzdxq<?>) this);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean setException(Throwable th) {
        if (!zzhru.zza((zzdxq<?>) this, (Object) null, (Object) new zzc((Throwable) zzdwd.checkNotNull(th)))) {
            return false;
        }
        zza((zzdxq<?>) this);
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean setFuture(zzdzc<? extends V> zzdzc) {
        zzg zzg2;
        zzc zzc2;
        zzdwd.checkNotNull(zzdzc);
        Object obj = this.value;
        if (obj == null) {
            if (zzdzc.isDone()) {
                if (!zzhru.zza((zzdxq<?>) this, (Object) null, getFutureValue(zzdzc))) {
                    return false;
                }
                zza((zzdxq<?>) this);
                return true;
            }
            zzg2 = new zzg(this, zzdzc);
            if (zzhru.zza((zzdxq<?>) this, (Object) null, (Object) zzg2)) {
                try {
                    zzdzc.addListener(zzg2, zzdyj.INSTANCE);
                } catch (Throwable unused) {
                    zzc2 = zzc.zzhrx;
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zza) {
            zzdzc.cancel(((zza) obj).wasInterrupted);
        }
        return false;
        zzhru.zza((zzdxq<?>) this, (Object) zzg2, (Object) zzc2);
        return true;
    }

    /* access modifiers changed from: private */
    public static Object getFutureValue(zzdzc<?> zzdzc) {
        Throwable zza2;
        if (zzdzc instanceof zzi) {
            Object obj = ((zzdxq) zzdzc).value;
            if (!(obj instanceof zza)) {
                return obj;
            }
            zza zza3 = (zza) obj;
            if (!zza3.wasInterrupted) {
                return obj;
            }
            if (zza3.cause != null) {
                return new zza(false, zza3.cause);
            }
            return zza.zzhrw;
        } else if ((zzdzc instanceof zzdzt) && (zza2 = zzdzw.zza((zzdzt) zzdzc)) != null) {
            return new zzc(zza2);
        } else {
            boolean isCancelled = zzdzc.isCancelled();
            if ((!GENERATE_CANCELLATION_CAUSES) && isCancelled) {
                return zza.zzhrw;
            }
            try {
                Object uninterruptibly = getUninterruptibly(zzdzc);
                if (!isCancelled) {
                    return uninterruptibly == null ? NULL : uninterruptibly;
                }
                String valueOf = String.valueOf(zzdzc);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 84);
                sb.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb.append(valueOf);
                return new zza(false, new IllegalArgumentException(sb.toString()));
            } catch (ExecutionException e) {
                if (!isCancelled) {
                    return new zzc(e.getCause());
                }
                String valueOf2 = String.valueOf(zzdzc);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 84);
                sb2.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb2.append(valueOf2);
                return new zza(false, new IllegalArgumentException(sb2.toString(), e));
            } catch (CancellationException e2) {
                if (isCancelled) {
                    return new zza(false, e2);
                }
                String valueOf3 = String.valueOf(zzdzc);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 77);
                sb3.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
                sb3.append(valueOf3);
                return new zzc(new IllegalArgumentException(sb3.toString(), e2));
            } catch (Throwable th) {
                return new zzc(th);
            }
        }
    }

    private static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    /* access modifiers changed from: private */
    public static void zza(zzdxq<?> zzdxq) {
        zzdxq<V> zzdxq2;
        zze zze2;
        zze zze3;
        zze zze4 = null;
        zzdxq<?> zzdxq3 = zzdxq;
        while (true) {
            zzk zzk2 = zzdxq3.waiters;
            if (zzhru.zza(zzdxq3, zzk2, zzk.zzhsg)) {
                while (zzk2 != null) {
                    Thread thread = zzk2.thread;
                    if (thread != null) {
                        zzk2.thread = null;
                        LockSupport.unpark(thread);
                    }
                    zzk2 = zzk2.next;
                }
                zzdxq3.afterDone();
                do {
                    zze2 = zzdxq3.listeners;
                } while (!zzhru.zza(zzdxq3, zze2, zze.zzhry));
                while (true) {
                    zze3 = zze4;
                    zze4 = zze2;
                    if (zze4 == null) {
                        break;
                    }
                    zze2 = zze4.next;
                    zze4.next = zze3;
                }
                while (zze3 != null) {
                    zze4 = zze3.next;
                    Runnable runnable = zze3.task;
                    if (runnable instanceof zzg) {
                        zzg zzg2 = (zzg) runnable;
                        zzdxq<V> zzdxq4 = zzg2.zzhrz;
                        if (zzdxq4.value == zzg2) {
                            if (zzhru.zza((zzdxq<?>) zzdxq4, (Object) zzg2, getFutureValue(zzg2.future))) {
                                zzdxq2 = zzdxq4;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        zza(runnable, zze3.executor);
                    }
                    zze3 = zze4;
                }
                return;
            }
            zzdxq2 = zzdxq3;
            zzdxq3 = zzdxq2;
        }
    }

    /* access modifiers changed from: protected */
    @NullableDecl
    public final Throwable zzaxv() {
        if (!(this instanceof zzi)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).exception;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void maybePropagateCancellationTo(@NullableDecl Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zza(sb);
        } else {
            int length = sb.length();
            sb.append("PENDING");
            Object obj = this.value;
            if (obj instanceof zzg) {
                sb.append(", setFuture=[");
                zza(sb, (Object) ((zzg) obj).future);
                sb.append("]");
            } else {
                try {
                    str = zzdwf.emptyToNull(pendingToString());
                } catch (RuntimeException | StackOverflowError e) {
                    String valueOf = String.valueOf(e.getClass());
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 38);
                    sb2.append("Exception thrown from implementation: ");
                    sb2.append(valueOf);
                    str = sb2.toString();
                }
                if (str != null) {
                    sb.append(", info=[");
                    sb.append(str);
                    sb.append("]");
                }
            }
            if (isDone()) {
                sb.delete(length, sb.length());
                zza(sb);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @NullableDecl
    public String pendingToString() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        StringBuilder sb = new StringBuilder(41);
        sb.append("remaining delay=[");
        sb.append(delay);
        sb.append(" ms]");
        return sb.toString();
    }

    private final void zza(StringBuilder sb) {
        try {
            Object uninterruptibly = getUninterruptibly(this);
            sb.append("SUCCESS, result=[");
            zza(sb, uninterruptibly);
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private final void zza(StringBuilder sb, Object obj) {
        if (obj == this) {
            try {
                sb.append("this future");
            } catch (RuntimeException | StackOverflowError e) {
                sb.append("Exception thrown from implementation: ");
                sb.append(e.getClass());
            }
        } else {
            sb.append(obj);
        }
    }

    private static void zza(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzhrt;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", sb.toString(), e);
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zzb zzb2;
        Class<zzdxq> cls = zzdxq.class;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        GENERATE_CANCELLATION_CAUSES = z;
        zzhrt = Logger.getLogger(cls.getName());
        try {
            zzb2 = new zzj();
            th2 = null;
            th = null;
        } catch (Throwable th3) {
            th = th3;
            th2 = th;
            zzb2 = new zzf();
        }
        zzhru = zzb2;
        if (th != null) {
            zzhrt.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME, "UnsafeAtomicHelper is broken!", th2);
            zzhrt.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME, "SafeAtomicHelper is broken!", th);
        }
    }
}
