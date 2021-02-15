package net.bytebuddy;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.utility.CompoundList;

public class TypeCache<T> extends ReferenceQueue<ClassLoader> {
    private static final Class<?> NOT_FOUND = null;
    protected final ConcurrentMap<StorageKey, ConcurrentMap<T, Reference<Class<?>>>> cache = new ConcurrentHashMap();
    protected final Sort sort;

    public enum Sort {
        WEAK {
            /* access modifiers changed from: protected */
            public Reference<Class<?>> wrap(Class<?> cls) {
                return new WeakReference(cls);
            }
        },
        SOFT {
            /* access modifiers changed from: protected */
            public Reference<Class<?>> wrap(Class<?> cls) {
                return new SoftReference(cls);
            }
        };

        /* access modifiers changed from: protected */
        public abstract Reference<Class<?>> wrap(Class<?> cls);
    }

    public TypeCache(Sort sort2) {
        this.sort = sort2;
    }

    public Class<?> find(ClassLoader classLoader, T t) {
        ConcurrentMap concurrentMap = (ConcurrentMap) this.cache.get(new LookupKey(classLoader));
        if (concurrentMap == null) {
            return NOT_FOUND;
        }
        Reference reference = (Reference) concurrentMap.get(t);
        if (reference == null) {
            return NOT_FOUND;
        }
        return (Class) reference.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r0 = new java.util.concurrent.ConcurrentHashMap();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Class<?> insert(java.lang.ClassLoader r4, T r5, java.lang.Class<?> r6) {
        /*
            r3 = this;
            java.util.concurrent.ConcurrentMap<net.bytebuddy.TypeCache$StorageKey, java.util.concurrent.ConcurrentMap<T, java.lang.ref.Reference<java.lang.Class<?>>>> r0 = r3.cache
            net.bytebuddy.TypeCache$LookupKey r1 = new net.bytebuddy.TypeCache$LookupKey
            r1.<init>(r4)
            java.lang.Object r0 = r0.get(r1)
            java.util.concurrent.ConcurrentMap r0 = (java.util.concurrent.ConcurrentMap) r0
            if (r0 != 0) goto L_0x0024
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            java.util.concurrent.ConcurrentMap<net.bytebuddy.TypeCache$StorageKey, java.util.concurrent.ConcurrentMap<T, java.lang.ref.Reference<java.lang.Class<?>>>> r1 = r3.cache
            net.bytebuddy.TypeCache$StorageKey r2 = new net.bytebuddy.TypeCache$StorageKey
            r2.<init>(r4, r3)
            java.lang.Object r4 = r1.putIfAbsent(r2, r0)
            java.util.concurrent.ConcurrentMap r4 = (java.util.concurrent.ConcurrentMap) r4
            if (r4 == 0) goto L_0x0024
            r0 = r4
        L_0x0024:
            net.bytebuddy.TypeCache$Sort r4 = r3.sort
            java.lang.ref.Reference r4 = r4.wrap(r6)
            java.lang.Object r1 = r0.putIfAbsent(r5, r4)
            java.lang.ref.Reference r1 = (java.lang.ref.Reference) r1
        L_0x0030:
            if (r1 == 0) goto L_0x0057
            java.lang.Object r2 = r1.get()
            java.lang.Class r2 = (java.lang.Class) r2
            if (r2 == 0) goto L_0x003b
            return r2
        L_0x003b:
            boolean r1 = r0.remove(r5, r1)
            if (r1 == 0) goto L_0x0048
            java.lang.Object r1 = r0.putIfAbsent(r5, r4)
            java.lang.ref.Reference r1 = (java.lang.ref.Reference) r1
            goto L_0x0030
        L_0x0048:
            java.lang.Object r1 = r0.get(r5)
            java.lang.ref.Reference r1 = (java.lang.ref.Reference) r1
            if (r1 != 0) goto L_0x0030
            java.lang.Object r1 = r0.putIfAbsent(r5, r4)
            java.lang.ref.Reference r1 = (java.lang.ref.Reference) r1
            goto L_0x0030
        L_0x0057:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.TypeCache.insert(java.lang.ClassLoader, java.lang.Object, java.lang.Class):java.lang.Class");
    }

    public Class<?> findOrInsert(ClassLoader classLoader, T t, Callable<Class<?>> callable) {
        Class<?> find = find(classLoader, t);
        if (find != null) {
            return find;
        }
        try {
            return insert(classLoader, t, callable.call());
        } catch (Throwable th) {
            throw new IllegalArgumentException("Could not create type", th);
        }
    }

    public Class<?> findOrInsert(ClassLoader classLoader, T t, Callable<Class<?>> callable, Object obj) {
        Class<?> findOrInsert;
        Class<?> find = find(classLoader, t);
        if (find != null) {
            return find;
        }
        synchronized (obj) {
            findOrInsert = findOrInsert(classLoader, t, callable);
        }
        return findOrInsert;
    }

    public void expungeStaleEntries() {
        while (true) {
            Reference poll = poll();
            if (poll != null) {
                this.cache.remove(poll);
            } else {
                return;
            }
        }
    }

    public void clear() {
        this.cache.clear();
    }

    protected static class LookupKey {
        /* access modifiers changed from: private */
        public final ClassLoader classLoader;
        /* access modifiers changed from: private */
        public final int hashCode;

        protected LookupKey(ClassLoader classLoader2) {
            this.classLoader = classLoader2;
            this.hashCode = System.identityHashCode(classLoader2);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LookupKey) {
                if (this.classLoader == ((LookupKey) obj).classLoader) {
                    return true;
                }
                return false;
            } else if (!(obj instanceof StorageKey)) {
                return false;
            } else {
                StorageKey storageKey = (StorageKey) obj;
                if (this.hashCode == storageKey.hashCode && this.classLoader == storageKey.get()) {
                    return true;
                }
                return false;
            }
        }
    }

    protected static class StorageKey extends WeakReference<ClassLoader> {
        /* access modifiers changed from: private */
        public final int hashCode;

        protected StorageKey(ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue) {
            super(classLoader, referenceQueue);
            this.hashCode = System.identityHashCode(classLoader);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LookupKey) {
                LookupKey lookupKey = (LookupKey) obj;
                if (this.hashCode == lookupKey.hashCode && get() == lookupKey.classLoader) {
                    return true;
                }
                return false;
            } else if (!(obj instanceof StorageKey)) {
                return false;
            } else {
                StorageKey storageKey = (StorageKey) obj;
                if (this.hashCode == storageKey.hashCode && get() == storageKey.get()) {
                    return true;
                }
                return false;
            }
        }
    }

    public static class WithInlineExpunction<S> extends TypeCache<S> {
        public WithInlineExpunction(Sort sort) {
            super(sort);
        }

        public Class<?> find(ClassLoader classLoader, S s) {
            try {
                return TypeCache.super.find(classLoader, s);
            } finally {
                expungeStaleEntries();
            }
        }

        public Class<?> insert(ClassLoader classLoader, S s, Class<?> cls) {
            try {
                return TypeCache.super.insert(classLoader, s, cls);
            } finally {
                expungeStaleEntries();
            }
        }

        public Class<?> findOrInsert(ClassLoader classLoader, S s, Callable<Class<?>> callable) {
            try {
                return TypeCache.super.findOrInsert(classLoader, s, callable);
            } finally {
                expungeStaleEntries();
            }
        }

        public Class<?> findOrInsert(ClassLoader classLoader, S s, Callable<Class<?>> callable, Object obj) {
            try {
                return TypeCache.super.findOrInsert(classLoader, s, callable, obj);
            } finally {
                expungeStaleEntries();
            }
        }
    }

    public static class SimpleKey {
        private final Set<String> types;

        public SimpleKey(Class<?> cls, Class<?>... clsArr) {
            this(cls, (Collection<? extends Class<?>>) Arrays.asList(clsArr));
        }

        public SimpleKey(Class<?> cls, Collection<? extends Class<?>> collection) {
            this(CompoundList.of(cls, new ArrayList(collection)));
        }

        public SimpleKey(Collection<? extends Class<?>> collection) {
            this.types = new HashSet();
            for (Class name : collection) {
                this.types.add(name.getName());
            }
        }

        public int hashCode() {
            return this.types.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.types.equals(((SimpleKey) obj).types);
        }
    }
}
