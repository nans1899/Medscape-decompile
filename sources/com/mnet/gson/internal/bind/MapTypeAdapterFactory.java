package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.internal.b;
import com.mnet.gson.internal.c;
import com.mnet.gson.internal.d;
import com.mnet.gson.k;
import com.mnet.gson.q;
import com.mnet.gson.t;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class MapTypeAdapterFactory implements w {
    final boolean a;
    private final c b;

    public MapTypeAdapterFactory(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        Type b2 = gVar.b();
        if (!Map.class.isAssignableFrom(gVar.a())) {
            return null;
        }
        Type[] b3 = b.b(b2, b.e(b2));
        return new a(eVar, b3[0], a(eVar, b3[0]), b3[1], eVar.a(g.a(b3[1])), this.b.a(gVar));
    }

    private v<?> a(e eVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return i.f;
        }
        return eVar.a(g.a(type));
    }

    private final class a<K, V> extends v<Map<K, V>> {
        private final v<K> b;
        private final v<V> c;
        private final com.mnet.gson.internal.g<? extends Map<K, V>> d;

        public a(e eVar, Type type, v<K> vVar, Type type2, v<V> vVar2, com.mnet.gson.internal.g<? extends Map<K, V>> gVar) {
            this.b = new h(eVar, vVar, type);
            this.c = new h(eVar, vVar2, type2);
            this.d = gVar;
        }

        /* renamed from: a */
        public Map<K, V> read(h hVar) {
            i f = hVar.f();
            if (f == i.NULL) {
                hVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            if (f == i.BEGIN_ARRAY) {
                hVar.a();
                while (hVar.e()) {
                    hVar.a();
                    K read = this.b.read(hVar);
                    if (map.put(read, this.c.read(hVar)) == null) {
                        hVar.b();
                    } else {
                        throw new t("duplicate key: " + read);
                    }
                }
                hVar.b();
            } else {
                hVar.c();
                while (hVar.e()) {
                    d.a.a(hVar);
                    K read2 = this.b.read(hVar);
                    if (map.put(read2, this.c.read(hVar)) != null) {
                        throw new t("duplicate key: " + read2);
                    }
                }
                hVar.d();
            }
            return map;
        }

        /* renamed from: a */
        public void write(j jVar, Map<K, V> map) {
            if (map == null) {
                jVar.f();
            } else if (!MapTypeAdapterFactory.this.a) {
                jVar.d();
                for (Map.Entry next : map.entrySet()) {
                    jVar.a(String.valueOf(next.getKey()));
                    this.c.write(jVar, next.getValue());
                }
                jVar.e();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry next2 : map.entrySet()) {
                    k jsonTree = this.b.toJsonTree(next2.getKey());
                    arrayList.add(jsonTree);
                    arrayList2.add(next2.getValue());
                    z |= jsonTree.i() || jsonTree.j();
                }
                if (z) {
                    jVar.b();
                    while (i < arrayList.size()) {
                        jVar.b();
                        com.mnet.gson.internal.i.a((k) arrayList.get(i), jVar);
                        this.c.write(jVar, arrayList2.get(i));
                        jVar.c();
                        i++;
                    }
                    jVar.c();
                    return;
                }
                jVar.d();
                while (i < arrayList.size()) {
                    jVar.a(a((k) arrayList.get(i)));
                    this.c.write(jVar, arrayList2.get(i));
                    i++;
                }
                jVar.e();
            }
        }

        private String a(k kVar) {
            if (kVar.k()) {
                q o = kVar.o();
                if (o.r()) {
                    return String.valueOf(o.b());
                }
                if (o.a()) {
                    return Boolean.toString(o.h());
                }
                if (o.s()) {
                    return o.c();
                }
                throw new AssertionError();
            } else if (kVar.l()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }
}
