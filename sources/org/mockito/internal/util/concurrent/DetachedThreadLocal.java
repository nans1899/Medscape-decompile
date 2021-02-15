package org.mockito.internal.util.concurrent;

import org.mockito.internal.util.concurrent.WeakConcurrentMap;

public class DetachedThreadLocal<T> implements Runnable {
    final WeakConcurrentMap<Thread, T> map;

    public enum Cleaner {
        THREAD,
        INLINE,
        MANUAL
    }

    /* access modifiers changed from: protected */
    public T inheritValue(T t) {
        return t;
    }

    /* access modifiers changed from: protected */
    public T initialValue(Thread thread) {
        return null;
    }

    /* renamed from: org.mockito.internal.util.concurrent.DetachedThreadLocal$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.mockito.internal.util.concurrent.DetachedThreadLocal$Cleaner[] r0 = org.mockito.internal.util.concurrent.DetachedThreadLocal.Cleaner.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner = r0
                org.mockito.internal.util.concurrent.DetachedThreadLocal$Cleaner r1 = org.mockito.internal.util.concurrent.DetachedThreadLocal.Cleaner.THREAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner     // Catch:{ NoSuchFieldError -> 0x001d }
                org.mockito.internal.util.concurrent.DetachedThreadLocal$Cleaner r1 = org.mockito.internal.util.concurrent.DetachedThreadLocal.Cleaner.MANUAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.mockito.internal.util.concurrent.DetachedThreadLocal$Cleaner r1 = org.mockito.internal.util.concurrent.DetachedThreadLocal.Cleaner.INLINE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.util.concurrent.DetachedThreadLocal.AnonymousClass3.<clinit>():void");
        }
    }

    public DetachedThreadLocal(Cleaner cleaner) {
        int i = AnonymousClass3.$SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner[cleaner.ordinal()];
        boolean z = true;
        if (i == 1 || i == 2) {
            this.map = new WeakConcurrentMap<Thread, T>(cleaner != Cleaner.THREAD ? false : z) {
                /* access modifiers changed from: protected */
                public T defaultValue(Thread thread) {
                    return DetachedThreadLocal.this.initialValue(thread);
                }
            };
        } else if (i == 3) {
            this.map = new WeakConcurrentMap.WithInlinedExpunction<Thread, T>() {
                /* access modifiers changed from: protected */
                public T defaultValue(Thread thread) {
                    return DetachedThreadLocal.this.initialValue(thread);
                }
            };
        } else {
            throw new AssertionError();
        }
    }

    public T get() {
        return this.map.get(Thread.currentThread());
    }

    public void set(T t) {
        this.map.put(Thread.currentThread(), t);
    }

    public void clear() {
        this.map.remove(Thread.currentThread());
    }

    public void clearAll() {
        this.map.clear();
    }

    public T pushTo(Thread thread) {
        T t = get();
        if (t != null) {
            this.map.put(thread, inheritValue(t));
        }
        return t;
    }

    public T fetchFrom(Thread thread) {
        T t = this.map.get(thread);
        if (t != null) {
            set(inheritValue(t));
        }
        return t;
    }

    public T get(Thread thread) {
        return this.map.get(thread);
    }

    public void define(Thread thread, T t) {
        this.map.put(thread, t);
    }

    public WeakConcurrentMap<Thread, T> getBackingMap() {
        return this.map;
    }

    public void run() {
        this.map.run();
    }
}
