package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdyy<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable zzhti = new zzdyz();
    private static final Runnable zzhtj = new zzdyz();
    private static final Runnable zzhtk = new zzdyz();

    zzdyy() {
    }

    /* access modifiers changed from: package-private */
    public abstract boolean isDone();

    /* access modifiers changed from: package-private */
    public abstract T zzayd() throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String zzaye();

    /* access modifiers changed from: package-private */
    public abstract void zzb(@NullableDecl T t, @NullableDecl Throwable th);

    public final void run() {
        Object obj;
        Thread currentThread = Thread.currentThread();
        if (compareAndSet((Object) null, currentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    obj = zzayd();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, zzhti)) {
                        Runnable runnable = (Runnable) get();
                        boolean z2 = false;
                        int i = 0;
                        while (true) {
                            if (runnable != zzhtj && runnable != zzhtk) {
                                break;
                            }
                            i++;
                            if (i > 1000) {
                                Runnable runnable2 = zzhtk;
                                if (runnable == runnable2 || compareAndSet(zzhtj, runnable2)) {
                                    z2 = Thread.interrupted() || z2;
                                    LockSupport.park(this);
                                }
                            } else {
                                Thread.yield();
                            }
                            runnable = (Runnable) get();
                        }
                        if (z2) {
                            currentThread.interrupt();
                        }
                    }
                    if (z) {
                        zzb((Object) null, th);
                        return;
                    }
                    return;
                }
            } else {
                obj = null;
            }
            if (!compareAndSet(currentThread, zzhti)) {
                Runnable runnable3 = (Runnable) get();
                boolean z3 = false;
                int i2 = 0;
                while (true) {
                    if (runnable3 != zzhtj && runnable3 != zzhtk) {
                        break;
                    }
                    i2++;
                    if (i2 > 1000) {
                        Runnable runnable4 = zzhtk;
                        if (runnable3 == runnable4 || compareAndSet(zzhtj, runnable4)) {
                            z3 = Thread.interrupted() || z3;
                            LockSupport.park(this);
                        }
                    } else {
                        Thread.yield();
                    }
                    runnable3 = (Runnable) get();
                }
                if (z3) {
                    currentThread.interrupt();
                }
            }
            if (z) {
                zzb(obj, (Throwable) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable runnable = (Runnable) get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, zzhtj)) {
            try {
                ((Thread) runnable).interrupt();
            } finally {
                if (((Runnable) getAndSet(zzhti)) == zzhtk) {
                    LockSupport.unpark((Thread) runnable);
                }
            }
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == zzhti) {
            str = "running=[DONE]";
        } else if (runnable == zzhtj) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21);
            sb.append("running=[RUNNING ON ");
            sb.append(name);
            sb.append("]");
            str = sb.toString();
        } else {
            str = "running=[NOT STARTED YET]";
        }
        String zzaye = zzaye();
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(zzaye).length());
        sb2.append(str);
        sb2.append(", ");
        sb2.append(zzaye);
        return sb2.toString();
    }
}
