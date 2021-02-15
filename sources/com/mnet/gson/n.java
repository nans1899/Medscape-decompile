package com.mnet.gson;

import com.mnet.gson.internal.f;
import java.util.Map;
import java.util.Set;

public final class n extends k {
    private final f<String, k> a = new f<>();

    public void a(String str, k kVar) {
        if (kVar == null) {
            kVar = m.a;
        }
        this.a.put(str, kVar);
    }

    public void a(String str, String str2) {
        a(str, a((Object) str2));
    }

    public void a(String str, Number number) {
        a(str, a((Object) number));
    }

    private k a(Object obj) {
        return obj == null ? m.a : new q(obj);
    }

    public Set<Map.Entry<String, k>> a() {
        return this.a.entrySet();
    }

    public boolean a(String str) {
        return this.a.containsKey(str);
    }

    public k b(String str) {
        return this.a.get(str);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof n) && ((n) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
