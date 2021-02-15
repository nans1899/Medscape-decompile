package net.bytebuddy.dynamic.loading;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;

public abstract class InjectionClassLoader extends ClassLoader {
    private final boolean sealed;

    /* access modifiers changed from: protected */
    public abstract Map<String, Class<?>> doDefineClasses(Map<String, byte[]> map) throws ClassNotFoundException;

    protected InjectionClassLoader(ClassLoader classLoader, boolean z) {
        super(classLoader);
        this.sealed = z;
    }

    public boolean isSealed() {
        return this.sealed;
    }

    public Class<?> defineClass(String str, byte[] bArr) throws ClassNotFoundException {
        return defineClasses(Collections.singletonMap(str, bArr)).get(str);
    }

    public Map<String, Class<?>> defineClasses(Map<String, byte[]> map) throws ClassNotFoundException {
        if (!this.sealed) {
            return doDefineClasses(map);
        }
        throw new IllegalStateException("Cannot inject classes into a sealed class loader");
    }

    public enum Strategy implements ClassLoadingStrategy<InjectionClassLoader> {
        INSTANCE;

        public Map<TypeDescription, Class<?>> load(InjectionClassLoader injectionClassLoader, Map<TypeDescription, byte[]> map) {
            if (injectionClassLoader != null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                HashMap hashMap = new HashMap();
                for (Map.Entry next : map.entrySet()) {
                    linkedHashMap.put(((TypeDescription) next.getKey()).getName(), next.getValue());
                    hashMap.put(((TypeDescription) next.getKey()).getName(), next.getKey());
                }
                HashMap hashMap2 = new HashMap();
                try {
                    for (Map.Entry next2 : injectionClassLoader.defineClasses(linkedHashMap).entrySet()) {
                        hashMap2.put(hashMap.get(next2.getKey()), next2.getValue());
                    }
                    return hashMap2;
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Cannot load classes: " + map, e);
                }
            } else {
                throw new IllegalArgumentException("Cannot add types to bootstrap class loader: " + map);
            }
        }
    }
}
