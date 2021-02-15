package org.mockito.internal.util.concurrent;

import java.util.Iterator;
import java.util.Map;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;

public class WeakConcurrentSet<V> implements Runnable, Iterable<V> {
    final WeakConcurrentMap<V, Boolean> target;

    public enum Cleaner {
        THREAD,
        INLINE,
        MANUAL
    }

    /* renamed from: org.mockito.internal.util.concurrent.WeakConcurrentSet$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.mockito.internal.util.concurrent.WeakConcurrentSet$Cleaner[] r0 = org.mockito.internal.util.concurrent.WeakConcurrentSet.Cleaner.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner = r0
                org.mockito.internal.util.concurrent.WeakConcurrentSet$Cleaner r1 = org.mockito.internal.util.concurrent.WeakConcurrentSet.Cleaner.INLINE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner     // Catch:{ NoSuchFieldError -> 0x001d }
                org.mockito.internal.util.concurrent.WeakConcurrentSet$Cleaner r1 = org.mockito.internal.util.concurrent.WeakConcurrentSet.Cleaner.THREAD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.mockito.internal.util.concurrent.WeakConcurrentSet$Cleaner r1 = org.mockito.internal.util.concurrent.WeakConcurrentSet.Cleaner.MANUAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.util.concurrent.WeakConcurrentSet.AnonymousClass1.<clinit>():void");
        }
    }

    public WeakConcurrentSet(Cleaner cleaner) {
        int i = AnonymousClass1.$SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner[cleaner.ordinal()];
        boolean z = true;
        if (i == 1) {
            this.target = new WeakConcurrentMap.WithInlinedExpunction();
        } else if (i == 2 || i == 3) {
            this.target = new WeakConcurrentMap<>(cleaner != Cleaner.THREAD ? false : z);
        } else {
            throw new AssertionError();
        }
    }

    public boolean add(V v) {
        return this.target.put(v, Boolean.TRUE) == null;
    }

    public boolean contains(V v) {
        return this.target.containsKey(v);
    }

    public boolean remove(V v) {
        return this.target.remove(v).booleanValue();
    }

    public void clear() {
        this.target.clear();
    }

    public int approximateSize() {
        return this.target.approximateSize();
    }

    public void run() {
        this.target.run();
    }

    public void expungeStaleEntries() {
        this.target.expungeStaleEntries();
    }

    public Thread getCleanerThread() {
        return this.target.getCleanerThread();
    }

    public Iterator<V> iterator() {
        return new ReducingIterator(this.target.iterator(), (AnonymousClass1) null);
    }

    private static class ReducingIterator<V> implements Iterator<V> {
        private final Iterator<Map.Entry<V, Boolean>> iterator;

        /* synthetic */ ReducingIterator(Iterator it, AnonymousClass1 r2) {
            this(it);
        }

        private ReducingIterator(Iterator<Map.Entry<V, Boolean>> it) {
            this.iterator = it;
        }

        public void remove() {
            this.iterator.remove();
        }

        public V next() {
            return this.iterator.next().getKey();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }
    }
}
