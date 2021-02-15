package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleMixInResolver implements ClassIntrospector.MixInResolver, Serializable {
    private static final long serialVersionUID = 1;
    protected Map<ClassKey, Class<?>> _localMixIns;
    protected final ClassIntrospector.MixInResolver _overrides;

    public SimpleMixInResolver(ClassIntrospector.MixInResolver mixInResolver) {
        this._overrides = mixInResolver;
    }

    protected SimpleMixInResolver(ClassIntrospector.MixInResolver mixInResolver, Map<ClassKey, Class<?>> map) {
        this._overrides = mixInResolver;
        this._localMixIns = map;
    }

    public SimpleMixInResolver withOverrides(ClassIntrospector.MixInResolver mixInResolver) {
        return new SimpleMixInResolver(mixInResolver, this._localMixIns);
    }

    public SimpleMixInResolver withoutLocalDefinitions() {
        return new SimpleMixInResolver(this._overrides, (Map<ClassKey, Class<?>>) null);
    }

    public void setLocalDefinitions(Map<Class<?>, Class<?>> map) {
        if (map == null || map.isEmpty()) {
            this._localMixIns = null;
            return;
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(new ClassKey((Class) next.getKey()), next.getValue());
        }
        this._localMixIns = hashMap;
    }

    public void addLocalDefinition(Class<?> cls, Class<?> cls2) {
        if (this._localMixIns == null) {
            this._localMixIns = new HashMap();
        }
        this._localMixIns.put(new ClassKey(cls), cls2);
    }

    public SimpleMixInResolver copy() {
        ClassIntrospector.MixInResolver mixInResolver = this._overrides;
        HashMap hashMap = null;
        ClassIntrospector.MixInResolver copy = mixInResolver == null ? null : mixInResolver.copy();
        if (this._localMixIns != null) {
            hashMap = new HashMap(this._localMixIns);
        }
        return new SimpleMixInResolver(copy, hashMap);
    }

    public Class<?> findMixInClassFor(Class<?> cls) {
        Map<ClassKey, Class<?>> map;
        ClassIntrospector.MixInResolver mixInResolver = this._overrides;
        Class<?> findMixInClassFor = mixInResolver == null ? null : mixInResolver.findMixInClassFor(cls);
        return (findMixInClassFor != null || (map = this._localMixIns) == null) ? findMixInClassFor : map.get(new ClassKey(cls));
    }

    public int localSize() {
        Map<ClassKey, Class<?>> map = this._localMixIns;
        if (map == null) {
            return 0;
        }
        return map.size();
    }
}
