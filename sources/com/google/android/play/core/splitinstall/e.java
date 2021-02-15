package com.google.android.play.core.splitinstall;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class e {
    private final Map<String, Map<String, String>> a = new HashMap();

    public final f a() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.a.entrySet()) {
            hashMap.put((String) next.getKey(), Collections.unmodifiableMap(new HashMap((Map) next.getValue())));
        }
        return new f(Collections.unmodifiableMap(hashMap));
    }

    public final void a(String str, String str2, String str3) {
        if (!this.a.containsKey(str2)) {
            this.a.put(str2, new HashMap());
        }
        this.a.get(str2).put(str, str3);
    }
}
