package net.bytebuddy.dynamic;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Nexus extends WeakReference<ClassLoader> {
    protected static final ReferenceQueue<ClassLoader> NO_QUEUE = null;
    public static final String PROPERTY = "net.bytebuddy.nexus.disabled";
    private static final ConcurrentMap<Nexus, Object> TYPE_INITIALIZERS = new ConcurrentHashMap();
    private final int classLoaderHashCode;
    private final int identification;
    private final String name;

    private Nexus(Class<?> cls, int i) {
        this(nonAnonymous(cls.getName()), cls.getClassLoader(), NO_QUEUE, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Nexus(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i) {
        super(classLoader, classLoader == null ? null : referenceQueue);
        this.name = str;
        this.classLoaderHashCode = System.identityHashCode(classLoader);
        this.identification = i;
    }

    private static String nonAnonymous(String str) {
        int indexOf = str.indexOf(47);
        return indexOf == -1 ? str : str.substring(0, indexOf);
    }

    public static void initialize(Class<?> cls, int i) throws Exception {
        Object remove = TYPE_INITIALIZERS.remove(new Nexus(cls, i));
        if (remove != null) {
            remove.getClass().getMethod("onLoad", new Class[]{Class.class}).invoke(remove, new Object[]{cls});
        }
    }

    public static void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, Object obj) {
        TYPE_INITIALIZERS.put(new Nexus(str, classLoader, referenceQueue, i), obj);
    }

    public static void clean(Reference<? super ClassLoader> reference) {
        TYPE_INITIALIZERS.remove(reference);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.classLoaderHashCode) * 31) + this.identification;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nexus nexus = (Nexus) obj;
        if (this.classLoaderHashCode == nexus.classLoaderHashCode && this.identification == nexus.identification && this.name.equals(nexus.name) && get() == nexus.get()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Nexus{name='" + this.name + '\'' + ", classLoaderHashCode=" + this.classLoaderHashCode + ", identification=" + this.identification + ", classLoader=" + get() + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
