package com.google.android.play.core.splitinstall;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class f {
    private final Map<String, Map<String, String>> a;

    /* synthetic */ f(Map map) {
        this.a = map;
    }

    public final Map<String, Set<String>> a(Collection<String> collection) {
        Set set;
        HashMap hashMap = new HashMap();
        for (String next : this.a.keySet()) {
            if (!this.a.containsKey(next)) {
                set = Collections.emptySet();
            } else {
                HashSet hashSet = new HashSet();
                for (Map.Entry entry : this.a.get(next).entrySet()) {
                    if (collection.contains(entry.getKey())) {
                        hashSet.add((String) entry.getValue());
                    }
                }
                set = Collections.unmodifiableSet(hashSet);
            }
            hashMap.put(next, set);
        }
        return hashMap;
    }
}
