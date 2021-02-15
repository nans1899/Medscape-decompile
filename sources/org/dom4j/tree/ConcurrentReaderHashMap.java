package org.dom4j.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

class ConcurrentReaderHashMap extends AbstractMap implements Map, Cloneable, Serializable {
    public static int DEFAULT_INITIAL_CAPACITY = 32;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final int MINIMUM_CAPACITY = 4;
    protected final BarrierLock barrierLock;
    protected transient int count;
    protected transient Set entrySet;
    protected transient Set keySet;
    protected transient Object lastWrite;
    protected float loadFactor;
    protected transient Entry[] table;
    protected int threshold;
    protected transient Collection values;

    private int p2capacity(int i) {
        int i2 = 1073741824;
        if (i <= 1073741824 && i >= 0) {
            i2 = 4;
            while (i2 < i) {
                i2 <<= 1;
            }
        }
        return i2;
    }

    protected static class BarrierLock implements Serializable {
        protected BarrierLock() {
        }
    }

    /* access modifiers changed from: protected */
    public final void recordModification(Object obj) {
        synchronized (this.barrierLock) {
            this.lastWrite = obj;
        }
    }

    /* access modifiers changed from: protected */
    public final Entry[] getTableForReading() {
        Entry[] entryArr;
        synchronized (this.barrierLock) {
            entryArr = this.table;
        }
        return entryArr;
    }

    private static int hash(Object obj) {
        int hashCode = obj.hashCode();
        return ((hashCode << 7) - hashCode) + (hashCode >>> 9) + (hashCode >>> 17);
    }

    /* access modifiers changed from: protected */
    public boolean eq(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    public ConcurrentReaderHashMap(int i, float f) {
        this.barrierLock = new BarrierLock();
        this.keySet = null;
        this.entrySet = null;
        this.values = null;
        if (f > 0.0f) {
            this.loadFactor = f;
            int p2capacity = p2capacity(i);
            this.table = new Entry[p2capacity];
            this.threshold = (int) (((float) p2capacity) * f);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("Illegal Load factor: ");
        stringBuffer.append(f);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public ConcurrentReaderHashMap(int i) {
        this(i, 0.75f);
    }

    public ConcurrentReaderHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, 0.75f);
    }

    public ConcurrentReaderHashMap(Map map) {
        this(Math.max(((int) (((float) map.size()) / 0.75f)) + 1, 16), 0.75f);
        putAll(map);
    }

    public synchronized int size() {
        return this.count;
    }

    public synchronized boolean isEmpty() {
        return this.count == 0;
    }

    public Object get(Object obj) {
        int hash = hash(obj);
        Entry[] entryArr = this.table;
        int length = (entryArr.length - 1) & hash;
        Entry entry = entryArr[length];
        Entry entry2 = entry;
        while (true) {
            if (entry == null) {
                Entry[] tableForReading = getTableForReading();
                if (entryArr == tableForReading && entry2 == entryArr[length]) {
                    return null;
                }
                length = hash & (tableForReading.length - 1);
                entry2 = tableForReading[length];
                entryArr = tableForReading;
            } else if (entry.hash != hash || !eq(obj, entry.key)) {
                entry = entry.next;
            } else {
                Object obj2 = entry.value;
                if (obj2 != null) {
                    return obj2;
                }
                synchronized (this) {
                    entryArr = this.table;
                }
                length = (entryArr.length - 1) & hash;
                entry2 = entryArr[length];
            }
            entry = entry2;
        }
        while (true) {
        }
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object put(java.lang.Object r8, java.lang.Object r9) {
        /*
            r7 = this;
            r0 = 0
            if (r9 == 0) goto L_0x005c
            int r1 = hash(r8)
            org.dom4j.tree.ConcurrentReaderHashMap$Entry[] r2 = r7.table
            int r3 = r2.length
            int r3 = r3 + -1
            r3 = r3 & r1
            r4 = r2[r3]
            r5 = r4
        L_0x0010:
            if (r5 != 0) goto L_0x0013
            goto L_0x001f
        L_0x0013:
            int r6 = r5.hash
            if (r6 != r1) goto L_0x0059
            java.lang.Object r6 = r5.key
            boolean r6 = r7.eq(r8, r6)
            if (r6 == 0) goto L_0x0059
        L_0x001f:
            monitor-enter(r7)
            org.dom4j.tree.ConcurrentReaderHashMap$Entry[] r6 = r7.table     // Catch:{ all -> 0x0056 }
            if (r2 != r6) goto L_0x0050
            if (r5 != 0) goto L_0x0044
            r5 = r2[r3]     // Catch:{ all -> 0x0056 }
            if (r4 != r5) goto L_0x0050
            org.dom4j.tree.ConcurrentReaderHashMap$Entry r5 = new org.dom4j.tree.ConcurrentReaderHashMap$Entry     // Catch:{ all -> 0x0056 }
            r5.<init>(r1, r8, r9, r4)     // Catch:{ all -> 0x0056 }
            r2[r3] = r5     // Catch:{ all -> 0x0056 }
            int r8 = r7.count     // Catch:{ all -> 0x0056 }
            int r8 = r8 + 1
            r7.count = r8     // Catch:{ all -> 0x0056 }
            int r9 = r7.threshold     // Catch:{ all -> 0x0056 }
            if (r8 < r9) goto L_0x003f
            r7.rehash()     // Catch:{ all -> 0x0056 }
            goto L_0x0042
        L_0x003f:
            r7.recordModification(r5)     // Catch:{ all -> 0x0056 }
        L_0x0042:
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            return r0
        L_0x0044:
            java.lang.Object r0 = r5.value     // Catch:{ all -> 0x0056 }
            r2 = r2[r3]     // Catch:{ all -> 0x0056 }
            if (r4 != r2) goto L_0x0050
            if (r0 == 0) goto L_0x0050
            r5.value = r9     // Catch:{ all -> 0x0056 }
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            return r0
        L_0x0050:
            java.lang.Object r8 = r7.sput(r8, r9, r1)     // Catch:{ all -> 0x0056 }
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            return r8
        L_0x0056:
            r8 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            throw r8
        L_0x0059:
            org.dom4j.tree.ConcurrentReaderHashMap$Entry r5 = r5.next
            goto L_0x0010
        L_0x005c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.tree.ConcurrentReaderHashMap.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Object sput(Object obj, Object obj2, int i) {
        Entry[] entryArr = this.table;
        int length = (entryArr.length - 1) & i;
        Entry entry = entryArr[length];
        Entry entry2 = entry;
        while (entry2 != null) {
            if (entry2.hash != i || !eq(obj, entry2.key)) {
                entry2 = entry2.next;
            } else {
                Object obj3 = entry2.value;
                entry2.value = obj2;
                return obj3;
            }
        }
        Entry entry3 = new Entry(i, obj, obj2, entry);
        entryArr[length] = entry3;
        int i2 = this.count + 1;
        this.count = i2;
        if (i2 >= this.threshold) {
            rehash();
            return null;
        }
        recordModification(entry3);
        return null;
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        if (r1 >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i = r1 << 1;
        int i2 = i - 1;
        this.threshold = (int) (((float) i) * this.loadFactor);
        Entry[] entryArr = new Entry[i];
        for (Entry entry : this.table) {
            if (entry != null) {
                int i3 = entry.hash & i2;
                Entry entry2 = entry.next;
                if (entry2 == null) {
                    entryArr[i3] = entry;
                } else {
                    Entry entry3 = entry;
                    while (entry2 != null) {
                        int i4 = entry2.hash & i2;
                        if (i4 != i3) {
                            entry3 = entry2;
                            i3 = i4;
                        }
                        entry2 = entry2.next;
                    }
                    entryArr[i3] = entry3;
                    while (entry != entry3) {
                        int i5 = entry.hash & i2;
                        entryArr[i5] = new Entry(entry.hash, entry.key, entry.value, entryArr[i5]);
                        entry = entry.next;
                    }
                }
            }
        }
        this.table = entryArr;
        recordModification(entryArr);
    }

    public Object remove(Object obj) {
        int hash = hash(obj);
        Entry[] entryArr = this.table;
        int length = (entryArr.length - 1) & hash;
        Entry entry = entryArr[length];
        Entry entry2 = entry;
        while (entry2 != null && (entry2.hash != hash || !eq(obj, entry2.key))) {
            entry2 = entry2.next;
        }
        synchronized (this) {
            if (entryArr == this.table) {
                if (entry2 != null) {
                    Object obj2 = entry2.value;
                    if (entry == entryArr[length] && obj2 != null) {
                        entry2.value = null;
                        this.count--;
                        Entry entry3 = entry2.next;
                        while (entry != entry2) {
                            Entry entry4 = new Entry(entry.hash, entry.key, entry.value, entry3);
                            entry = entry.next;
                            entry3 = entry4;
                        }
                        entryArr[length] = entry3;
                        recordModification(entry3);
                        return obj2;
                    }
                } else if (entry == entryArr[length]) {
                    return null;
                }
            }
            Object sremove = sremove(obj, hash);
            return sremove;
        }
    }

    /* access modifiers changed from: protected */
    public Object sremove(Object obj, int i) {
        Entry[] entryArr = this.table;
        int length = (entryArr.length - 1) & i;
        Entry entry = entryArr[length];
        Entry entry2 = entry;
        while (entry2 != null) {
            if (entry2.hash != i || !eq(obj, entry2.key)) {
                entry2 = entry2.next;
            } else {
                Object obj2 = entry2.value;
                entry2.value = null;
                this.count--;
                Entry entry3 = entry2.next;
                while (entry != entry2) {
                    Entry entry4 = new Entry(entry.hash, entry.key, entry.value, entry3);
                    entry = entry.next;
                    entry3 = entry4;
                }
                entryArr[length] = entry3;
                recordModification(entry3);
                return obj2;
            }
        }
        return null;
    }

    public boolean containsValue(Object obj) {
        if (obj != null) {
            Entry[] tableForReading = getTableForReading();
            for (Entry entry : tableForReading) {
                while (entry != null) {
                    if (obj.equals(entry.value)) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
            return false;
        }
        throw null;
    }

    public boolean contains(Object obj) {
        return containsValue(obj);
    }

    public synchronized void putAll(Map map) {
        int size = map.size();
        if (size != 0) {
            while (size >= this.threshold) {
                rehash();
            }
            for (Map.Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public synchronized void clear() {
        Entry[] entryArr = this.table;
        for (int i = 0; i < entryArr.length; i++) {
            for (Entry entry = entryArr[i]; entry != null; entry = entry.next) {
                entry.value = null;
            }
            entryArr[i] = null;
        }
        this.count = 0;
        recordModification(entryArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        throw new java.lang.InternalError();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0036 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object clone() {
        /*
            r11 = this;
            monitor-enter(r11)
            java.lang.Object r0 = super.clone()     // Catch:{ CloneNotSupportedException -> 0x0036 }
            org.dom4j.tree.ConcurrentReaderHashMap r0 = (org.dom4j.tree.ConcurrentReaderHashMap) r0     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r1 = 0
            r0.keySet = r1     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r0.entrySet = r1     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r0.values = r1     // Catch:{ CloneNotSupportedException -> 0x0036 }
            org.dom4j.tree.ConcurrentReaderHashMap$Entry[] r2 = r11.table     // Catch:{ CloneNotSupportedException -> 0x0036 }
            int r3 = r2.length     // Catch:{ CloneNotSupportedException -> 0x0036 }
            org.dom4j.tree.ConcurrentReaderHashMap$Entry[] r3 = new org.dom4j.tree.ConcurrentReaderHashMap.Entry[r3]     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r0.table = r3     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r4 = 0
        L_0x0016:
            int r5 = r2.length     // Catch:{ CloneNotSupportedException -> 0x0036 }
            if (r4 < r5) goto L_0x001b
            monitor-exit(r11)
            return r0
        L_0x001b:
            r5 = r2[r4]     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r6 = r1
        L_0x001e:
            if (r5 != 0) goto L_0x0025
            r3[r4] = r6     // Catch:{ CloneNotSupportedException -> 0x0036 }
            int r4 = r4 + 1
            goto L_0x0016
        L_0x0025:
            org.dom4j.tree.ConcurrentReaderHashMap$Entry r7 = new org.dom4j.tree.ConcurrentReaderHashMap$Entry     // Catch:{ CloneNotSupportedException -> 0x0036 }
            int r8 = r5.hash     // Catch:{ CloneNotSupportedException -> 0x0036 }
            java.lang.Object r9 = r5.key     // Catch:{ CloneNotSupportedException -> 0x0036 }
            java.lang.Object r10 = r5.value     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r7.<init>(r8, r9, r10, r6)     // Catch:{ CloneNotSupportedException -> 0x0036 }
            org.dom4j.tree.ConcurrentReaderHashMap$Entry r5 = r5.next     // Catch:{ CloneNotSupportedException -> 0x0036 }
            r6 = r7
            goto L_0x001e
        L_0x0034:
            r0 = move-exception
            goto L_0x003c
        L_0x0036:
            java.lang.InternalError r0 = new java.lang.InternalError     // Catch:{ all -> 0x0034 }
            r0.<init>()     // Catch:{ all -> 0x0034 }
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x003c:
            monitor-exit(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.tree.ConcurrentReaderHashMap.clone():java.lang.Object");
    }

    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet2 = new KeySet(this, (KeySet) null);
        this.keySet = keySet2;
        return keySet2;
    }

    private class KeySet extends AbstractSet {
        private KeySet() {
        }

        /* synthetic */ KeySet(ConcurrentReaderHashMap concurrentReaderHashMap, KeySet keySet) {
            this();
        }

        public Iterator iterator() {
            return new KeyIterator(ConcurrentReaderHashMap.this);
        }

        public int size() {
            return ConcurrentReaderHashMap.this.size();
        }

        public boolean contains(Object obj) {
            return ConcurrentReaderHashMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return ConcurrentReaderHashMap.this.remove(obj) != null;
        }

        public void clear() {
            ConcurrentReaderHashMap.this.clear();
        }
    }

    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values2 = new Values(this, (Values) null);
        this.values = values2;
        return values2;
    }

    private class Values extends AbstractCollection {
        private Values() {
        }

        /* synthetic */ Values(ConcurrentReaderHashMap concurrentReaderHashMap, Values values) {
            this();
        }

        public Iterator iterator() {
            return new ValueIterator(ConcurrentReaderHashMap.this);
        }

        public int size() {
            return ConcurrentReaderHashMap.this.size();
        }

        public boolean contains(Object obj) {
            return ConcurrentReaderHashMap.this.containsValue(obj);
        }

        public void clear() {
            ConcurrentReaderHashMap.this.clear();
        }
    }

    public Set entrySet() {
        Set set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet(this, (EntrySet) null);
        this.entrySet = entrySet2;
        return entrySet2;
    }

    private class EntrySet extends AbstractSet {
        private EntrySet() {
        }

        /* synthetic */ EntrySet(ConcurrentReaderHashMap concurrentReaderHashMap, EntrySet entrySet) {
            this();
        }

        public Iterator iterator() {
            return new HashIterator();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = ConcurrentReaderHashMap.this.get(entry.getKey());
            if (obj2 == null || !obj2.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            return ConcurrentReaderHashMap.this.findAndRemoveEntry((Map.Entry) obj);
        }

        public int size() {
            return ConcurrentReaderHashMap.this.size();
        }

        public void clear() {
            ConcurrentReaderHashMap.this.clear();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized boolean findAndRemoveEntry(Map.Entry entry) {
        Object key = entry.getKey();
        Object obj = get(key);
        if (obj == null || !obj.equals(entry.getValue())) {
            return false;
        }
        remove(key);
        return true;
    }

    public Enumeration keys() {
        return new KeyIterator(this);
    }

    public Enumeration elements() {
        return new ValueIterator(this);
    }

    protected static class Entry implements Map.Entry {
        protected final int hash;
        protected final Object key;
        protected final Entry next;
        protected volatile Object value;

        Entry(int i, Object obj, Object obj2, Entry entry) {
            this.hash = i;
            this.key = obj;
            this.next = entry;
            this.value = obj2;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object obj) {
            if (obj != null) {
                Object obj2 = this.value;
                this.value = obj;
                return obj2;
            }
            throw null;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!this.key.equals(entry.getKey()) || !this.value.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.key);
            stringBuffer.append("=");
            stringBuffer.append(this.value);
            return stringBuffer.toString();
        }
    }

    protected class HashIterator implements Iterator, Enumeration {
        protected Object currentKey;
        protected Object currentValue;
        protected Entry entry = null;
        protected int index;
        protected Entry lastReturned = null;
        protected final Entry[] tab;

        protected HashIterator() {
            Entry[] tableForReading = ConcurrentReaderHashMap.this.getTableForReading();
            this.tab = tableForReading;
            this.index = tableForReading.length - 1;
        }

        public boolean hasMoreElements() {
            return hasNext();
        }

        public Object nextElement() {
            return next();
        }

        public boolean hasNext() {
            int i;
            do {
                Entry entry2 = this.entry;
                if (entry2 != null) {
                    Object obj = entry2.value;
                    if (obj != null) {
                        this.currentKey = this.entry.key;
                        this.currentValue = obj;
                        return true;
                    }
                    this.entry = this.entry.next;
                }
                while (this.entry == null && (i = this.index) >= 0) {
                    Entry[] entryArr = this.tab;
                    this.index = i - 1;
                    this.entry = entryArr[i];
                }
            } while (this.entry != null);
            this.currentValue = null;
            this.currentKey = null;
            return false;
        }

        /* access modifiers changed from: protected */
        public Object returnValueOfNext() {
            return this.entry;
        }

        public Object next() {
            if (this.currentKey != null || hasNext()) {
                Object returnValueOfNext = returnValueOfNext();
                Entry entry2 = this.entry;
                this.lastReturned = entry2;
                this.currentValue = null;
                this.currentKey = null;
                this.entry = entry2.next;
                return returnValueOfNext;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            Entry entry2 = this.lastReturned;
            if (entry2 != null) {
                ConcurrentReaderHashMap.this.remove(entry2.key);
                this.lastReturned = null;
                return;
            }
            throw new IllegalStateException();
        }
    }

    protected class KeyIterator extends HashIterator {
        final /* synthetic */ ConcurrentReaderHashMap this$0;

        protected KeyIterator(ConcurrentReaderHashMap concurrentReaderHashMap) {
            super();
            this.this$0 = concurrentReaderHashMap;
        }

        /* access modifiers changed from: protected */
        public Object returnValueOfNext() {
            return this.currentKey;
        }
    }

    protected class ValueIterator extends HashIterator {
        final /* synthetic */ ConcurrentReaderHashMap this$0;

        protected ValueIterator(ConcurrentReaderHashMap concurrentReaderHashMap) {
            super();
            this.this$0 = concurrentReaderHashMap;
        }

        /* access modifiers changed from: protected */
        public Object returnValueOfNext() {
            return this.currentValue;
        }
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.table.length);
        objectOutputStream.writeInt(this.count);
        for (int length = this.table.length - 1; length >= 0; length--) {
            for (Entry entry = this.table[length]; entry != null; entry = entry.next) {
                objectOutputStream.writeObject(entry.key);
                objectOutputStream.writeObject(entry.value);
            }
        }
    }

    private synchronized void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.table = new Entry[objectInputStream.readInt()];
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    public synchronized int capacity() {
        return this.table.length;
    }

    public float loadFactor() {
        return this.loadFactor;
    }
}
