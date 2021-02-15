package org.mozilla.javascript;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.JavaAdapter;

public class ClassCache implements Serializable {
    private static final Object AKEY = "ClassCache";
    private static final long serialVersionUID = -8866246036237312215L;
    private Scriptable associatedScope;
    private volatile boolean cachingIsEnabled = true;
    private transient HashMap<JavaAdapter.JavaAdapterSignature, Class<?>> classAdapterCache;
    private transient HashMap<Class<?>, JavaMembers> classTable;
    private int generatedClassSerial;
    private transient HashMap<Class<?>, Object> interfaceAdapterCache;

    public boolean isInvokerOptimizationEnabled() {
        return false;
    }

    public static ClassCache get(Scriptable scriptable) {
        ClassCache classCache = (ClassCache) ScriptableObject.getTopScopeValue(scriptable, AKEY);
        if (classCache != null) {
            return classCache;
        }
        throw new RuntimeException("Can't find top level scope for ClassCache.get");
    }

    public boolean associate(ScriptableObject scriptableObject) {
        if (scriptableObject.getParentScope() != null) {
            throw new IllegalArgumentException();
        } else if (this != scriptableObject.associateValue(AKEY, this)) {
            return false;
        } else {
            this.associatedScope = scriptableObject;
            return true;
        }
    }

    public synchronized void clearCaches() {
        this.classTable = null;
        this.classAdapterCache = null;
        this.interfaceAdapterCache = null;
    }

    public final boolean isCachingEnabled() {
        return this.cachingIsEnabled;
    }

    public synchronized void setCachingEnabled(boolean z) {
        if (z != this.cachingIsEnabled) {
            if (!z) {
                clearCaches();
            }
            this.cachingIsEnabled = z;
        }
    }

    /* access modifiers changed from: package-private */
    public Map<Class<?>, JavaMembers> getClassCacheMap() {
        if (this.classTable == null) {
            this.classTable = new HashMap<>();
        }
        return this.classTable;
    }

    /* access modifiers changed from: package-private */
    public Map<JavaAdapter.JavaAdapterSignature, Class<?>> getInterfaceAdapterCacheMap() {
        if (this.classAdapterCache == null) {
            this.classAdapterCache = new HashMap<>();
        }
        return this.classAdapterCache;
    }

    public synchronized void setInvokerOptimizationEnabled(boolean z) {
    }

    public final synchronized int newClassSerialNumber() {
        int i;
        i = this.generatedClassSerial + 1;
        this.generatedClassSerial = i;
        return i;
    }

    /* access modifiers changed from: package-private */
    public Object getInterfaceAdapter(Class<?> cls) {
        HashMap<Class<?>, Object> hashMap = this.interfaceAdapterCache;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(cls);
    }

    /* access modifiers changed from: package-private */
    public synchronized void cacheInterfaceAdapter(Class<?> cls, Object obj) {
        if (this.cachingIsEnabled) {
            if (this.interfaceAdapterCache == null) {
                this.interfaceAdapterCache = new HashMap<>();
            }
            this.interfaceAdapterCache.put(cls, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public Scriptable getAssociatedScope() {
        return this.associatedScope;
    }
}
