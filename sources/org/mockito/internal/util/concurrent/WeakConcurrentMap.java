package org.mockito.internal.util.concurrent;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class WeakConcurrentMap<K, V> extends ReferenceQueue<K> implements Runnable, Iterable<Map.Entry<K, V>> {
    private static final AtomicLong ID = new AtomicLong();
    public final ConcurrentMap<WeakKey<K>, V> target = new ConcurrentHashMap();
    private final Thread thread;

    /* access modifiers changed from: protected */
    public V defaultValue(K k) {
        return null;
    }

    public WeakConcurrentMap(boolean z) {
        if (z) {
            Thread thread2 = new Thread(this);
            this.thread = thread2;
            thread2.setName("weak-ref-cleaner-" + ID.getAndIncrement());
            this.thread.setPriority(1);
            this.thread.setDaemon(true);
            this.thread.start();
            return;
        }
        this.thread = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r4 = r3.target.putIfAbsent(new org.mockito.internal.util.concurrent.WeakConcurrentMap.WeakKey(r4, r3), r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V get(K r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0024
            java.util.concurrent.ConcurrentMap<org.mockito.internal.util.concurrent.WeakConcurrentMap$WeakKey<K>, V> r0 = r3.target
            org.mockito.internal.util.concurrent.WeakConcurrentMap$LatentKey r1 = new org.mockito.internal.util.concurrent.WeakConcurrentMap$LatentKey
            r1.<init>(r4)
            java.lang.Object r0 = r0.get(r1)
            if (r0 != 0) goto L_0x0023
            java.lang.Object r0 = r3.defaultValue(r4)
            if (r0 == 0) goto L_0x0023
            java.util.concurrent.ConcurrentMap<org.mockito.internal.util.concurrent.WeakConcurrentMap$WeakKey<K>, V> r1 = r3.target
            org.mockito.internal.util.concurrent.WeakConcurrentMap$WeakKey r2 = new org.mockito.internal.util.concurrent.WeakConcurrentMap$WeakKey
            r2.<init>(r4, r3)
            java.lang.Object r4 = r1.putIfAbsent(r2, r0)
            if (r4 == 0) goto L_0x0023
            r0 = r4
        L_0x0023:
            return r0
        L_0x0024:
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.util.concurrent.WeakConcurrentMap.get(java.lang.Object):java.lang.Object");
    }

    public boolean containsKey(K k) {
        if (k != null) {
            return this.target.containsKey(new LatentKey(k));
        }
        throw null;
    }

    public V put(K k, V v) {
        if (k != null && v != null) {
            return this.target.put(new WeakKey(k, this), v);
        }
        throw null;
    }

    public V remove(K k) {
        if (k != null) {
            return this.target.remove(new LatentKey(k));
        }
        throw null;
    }

    public void clear() {
        this.target.clear();
    }

    public Thread getCleanerThread() {
        return this.thread;
    }

    public void expungeStaleEntries() {
        while (true) {
            Reference poll = poll();
            if (poll != null) {
                this.target.remove(poll);
            } else {
                return;
            }
        }
    }

    public int approximateSize() {
        return this.target.size();
    }

    public void run() {
        while (true) {
            try {
                this.target.remove(remove());
            } catch (InterruptedException unused) {
                clear();
                return;
            }
        }
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new EntryIterator(this.target.entrySet().iterator());
    }

    private static class WeakKey<T> extends WeakReference<T> {
        private final int hashCode;

        WeakKey(T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.hashCode = System.identityHashCode(t);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (obj instanceof LatentKey) {
                if (((LatentKey) obj).key == get()) {
                    return true;
                }
                return false;
            } else if (((WeakKey) obj).get() == get()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class LatentKey<T> {
        private final int hashCode;
        final T key;

        LatentKey(T t) {
            this.key = t;
            this.hashCode = System.identityHashCode(t);
        }

        public boolean equals(Object obj) {
            if (obj instanceof LatentKey) {
                if (((LatentKey) obj).key == this.key) {
                    return true;
                }
                return false;
            } else if (((WeakKey) obj).get() == this.key) {
                return true;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return this.hashCode;
        }
    }

    public static class WithInlinedExpunction<K, V> extends WeakConcurrentMap<K, V> {
        public WithInlinedExpunction() {
            super(false);
        }

        public V get(K k) {
            expungeStaleEntries();
            return WeakConcurrentMap.super.get(k);
        }

        public boolean containsKey(K k) {
            expungeStaleEntries();
            return WeakConcurrentMap.super.containsKey(k);
        }

        public V put(K k, V v) {
            expungeStaleEntries();
            return WeakConcurrentMap.super.put(k, v);
        }

        public V remove(K k) {
            expungeStaleEntries();
            return WeakConcurrentMap.super.remove(k);
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            expungeStaleEntries();
            return WeakConcurrentMap.super.iterator();
        }

        public int approximateSize() {
            expungeStaleEntries();
            return WeakConcurrentMap.super.approximateSize();
        }
    }

    private class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private final Iterator<Map.Entry<WeakKey<K>, V>> iterator;
        private Map.Entry<WeakKey<K>, V> nextEntry;
        private K nextKey;

        private EntryIterator(Iterator<Map.Entry<WeakKey<K>, V>> it) {
            this.iterator = it;
            findNext();
        }

        private void findNext() {
            while (this.iterator.hasNext()) {
                Map.Entry<WeakKey<K>, V> next = this.iterator.next();
                this.nextEntry = next;
                K k = next.getKey().get();
                this.nextKey = k;
                if (k != null) {
                    return;
                }
            }
            this.nextEntry = null;
            this.nextKey = null;
        }

        public boolean hasNext() {
            return this.nextKey != null;
        }

        public Map.Entry<K, V> next() {
            K k = this.nextKey;
            if (k != null) {
                try {
                    return new SimpleEntry(k, this.nextEntry);
                } finally {
                    findNext();
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class SimpleEntry implements Map.Entry<K, V> {
        final Map.Entry<WeakKey<K>, V> entry;
        private final K key;

        private SimpleEntry(K k, Map.Entry<WeakKey<K>, V> entry2) {
            this.key = k;
            this.entry = entry2;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.entry.getValue();
        }

        public V setValue(V v) {
            if (v != null) {
                return this.entry.setValue(v);
            }
            throw null;
        }
    }
}
