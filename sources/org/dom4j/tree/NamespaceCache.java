package org.dom4j.tree;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Map;
import org.dom4j.Namespace;

public class NamespaceCache {
    private static final String CONCURRENTREADERHASHMAP_CLASS = "EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap";
    protected static Map cache;
    protected static Map noPrefixCache;

    static {
        try {
            Constructor<?> constructor = Class.forName("java.util.concurrent.ConcurrentHashMap").getConstructor(new Class[]{Integer.TYPE, Float.TYPE, Integer.TYPE});
            cache = (Map) constructor.newInstance(new Object[]{new Integer(11), new Float(0.75f), new Integer(1)});
            noPrefixCache = (Map) constructor.newInstance(new Object[]{new Integer(11), new Float(0.75f), new Integer(1)});
        } catch (Throwable unused) {
            cache = new ConcurrentReaderHashMap();
            noPrefixCache = new ConcurrentReaderHashMap();
        }
    }

    public Namespace get(String str, String str2) {
        Map uRICache = getURICache(str2);
        WeakReference weakReference = (WeakReference) uRICache.get(str);
        Namespace namespace = weakReference != null ? (Namespace) weakReference.get() : null;
        if (namespace == null) {
            synchronized (uRICache) {
                WeakReference weakReference2 = (WeakReference) uRICache.get(str);
                if (weakReference2 != null) {
                    namespace = (Namespace) weakReference2.get();
                }
                if (namespace == null) {
                    Namespace createNamespace = createNamespace(str, str2);
                    uRICache.put(str, new WeakReference(createNamespace));
                    namespace = createNamespace;
                }
            }
        }
        return namespace;
    }

    public Namespace get(String str) {
        WeakReference weakReference = (WeakReference) noPrefixCache.get(str);
        Namespace namespace = weakReference != null ? (Namespace) weakReference.get() : null;
        if (namespace == null) {
            synchronized (noPrefixCache) {
                WeakReference weakReference2 = (WeakReference) noPrefixCache.get(str);
                if (weakReference2 != null) {
                    namespace = (Namespace) weakReference2.get();
                }
                if (namespace == null) {
                    namespace = createNamespace("", str);
                    noPrefixCache.put(str, new WeakReference(namespace));
                }
            }
        }
        return namespace;
    }

    /* access modifiers changed from: protected */
    public Map getURICache(String str) {
        Map map = (Map) cache.get(str);
        if (map == null) {
            synchronized (cache) {
                map = (Map) cache.get(str);
                if (map == null) {
                    map = new ConcurrentReaderHashMap();
                    cache.put(str, map);
                }
            }
        }
        return map;
    }

    /* access modifiers changed from: protected */
    public Namespace createNamespace(String str, String str2) {
        return new Namespace(str, str2);
    }
}
