package com.github.jasminb.jsonapi;

import java.util.HashMap;
import java.util.Map;

public class ResourceCache {
    private ThreadLocal<Boolean> cacheLocked = new ThreadLocal<>();
    private ThreadLocal<Integer> initDepth = new ThreadLocal<>();
    private ThreadLocal<Map<String, Object>> resourceCache = new ThreadLocal<>();

    public void init() {
        if (this.initDepth.get() == null) {
            this.initDepth.set(1);
        } else {
            ThreadLocal<Integer> threadLocal = this.initDepth;
            threadLocal.set(Integer.valueOf(threadLocal.get().intValue() + 1));
        }
        if (this.resourceCache.get() == null) {
            this.resourceCache.set(new HashMap());
        }
        if (this.cacheLocked.get() == null) {
            this.cacheLocked.set(Boolean.FALSE);
        }
    }

    public void clear() {
        verifyState();
        ThreadLocal<Integer> threadLocal = this.initDepth;
        threadLocal.set(Integer.valueOf(threadLocal.get().intValue() - 1));
        if (this.initDepth.get().intValue() == 0) {
            this.resourceCache.set((Object) null);
            this.cacheLocked.set((Object) null);
            this.initDepth.set((Object) null);
        }
    }

    public void cache(Map<String, Object> map) {
        verifyState();
        if (!this.cacheLocked.get().booleanValue()) {
            this.resourceCache.get().putAll(map);
        }
    }

    public void cache(String str, Object obj) {
        verifyState();
        if (!this.cacheLocked.get().booleanValue()) {
            this.resourceCache.get().put(str, obj);
        }
    }

    public Object get(String str) {
        verifyState();
        return this.resourceCache.get().get(str);
    }

    public boolean contains(String str) {
        verifyState();
        return this.resourceCache.get().containsKey(str);
    }

    public void lock() {
        verifyState();
        this.cacheLocked.set(true);
    }

    public void unlock() {
        verifyState();
        this.cacheLocked.set(false);
    }

    private void verifyState() {
        if (this.resourceCache.get() == null) {
            throw new IllegalStateException("Cache not initialised, call init() first");
        }
    }
}
