package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();

    /* access modifiers changed from: package-private */
    public abstract void afterRanInterruptibly(@NullableDecl T t, @NullableDecl Throwable th);

    /* access modifiers changed from: package-private */
    public abstract boolean isDone();

    /* access modifiers changed from: package-private */
    public abstract T runInterruptibly() throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String toPendingString();

    private static final class DoNothingRunnable implements Runnable {
        public void run() {
        }

        private DoNothingRunnable() {
        }
    }

    InterruptibleTask() {
    }

    public final void run() {
        Object obj;
        Thread currentThread = Thread.currentThread();
        if (compareAndSet((Object) null, currentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    obj = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, DONE)) {
                        while (get() == INTERRUPTING) {
                            Thread.yield();
                        }
                    }
                    if (z) {
                        afterRanInterruptibly((Object) null, th);
                        return;
                    }
                    return;
                }
            } else {
                obj = null;
            }
            if (!compareAndSet(currentThread, DONE)) {
                while (get() == INTERRUPTING) {
                    Thread.yield();
                }
            }
            if (z) {
                afterRanInterruptibly(obj, (Throwable) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable runnable = (Runnable) get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, INTERRUPTING)) {
            ((Thread) runnable).interrupt();
            set(DONE);
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return str + ", " + toPendingString();
    }
}
