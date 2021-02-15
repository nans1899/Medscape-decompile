package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.internal.i;
import com.mnet.gson.j;
import com.mnet.gson.k;
import com.mnet.gson.r;
import com.mnet.gson.s;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.Type;
import mnetinternal.g;
import mnetinternal.h;

public final class TreeTypeAdapter<T> extends v<T> {
    private final s<T> a;
    private final j<T> b;
    /* access modifiers changed from: private */
    public final e c;
    private final g<T> d;
    private final w e;
    private final TreeTypeAdapter<T>.a f = new a();
    private v<T> g;

    public TreeTypeAdapter(s<T> sVar, j<T> jVar, e eVar, g<T> gVar, w wVar) {
        this.a = sVar;
        this.b = jVar;
        this.c = eVar;
        this.d = gVar;
        this.e = wVar;
    }

    public T read(h hVar) {
        if (this.b == null) {
            return a().read(hVar);
        }
        k a2 = i.a(hVar);
        if (a2.l()) {
            return null;
        }
        return this.b.b(a2, this.d.b(), this.f);
    }

    public void write(mnetinternal.j jVar, T t) {
        s<T> sVar = this.a;
        if (sVar == null) {
            a().write(jVar, t);
        } else if (t == null) {
            jVar.f();
        } else {
            i.a(sVar.a(t, this.d.b(), this.f), jVar);
        }
    }

    private v<T> a() {
        v<T> vVar = this.g;
        if (vVar != null) {
            return vVar;
        }
        v<T> a2 = this.c.a(this.e, this.d);
        this.g = a2;
        return a2;
    }

    public static w a(g<?> gVar, Object obj) {
        return new SingleTypeFactory(obj, gVar, false, (Class<?>) null);
    }

    public static w b(g<?> gVar, Object obj) {
        return new SingleTypeFactory(obj, gVar, gVar.b() == gVar.a(), (Class<?>) null);
    }

    private static final class SingleTypeFactory implements w {
        private final g<?> a;
        private final boolean b;
        private final Class<?> c;
        private final s<?> d;
        private final j<?> e;

        SingleTypeFactory(Object obj, g<?> gVar, boolean z, Class<?> cls) {
            j<?> jVar = null;
            this.d = obj instanceof s ? (s) obj : null;
            jVar = obj instanceof j ? (j) obj : jVar;
            this.e = jVar;
            com.mnet.gson.internal.a.a((this.d == null && jVar == null) ? false : true);
            this.a = gVar;
            this.b = z;
            this.c = cls;
        }

        public <T> v<T> create(e eVar, g<T> gVar) {
            boolean z;
            g<?> gVar2 = this.a;
            if (gVar2 != null) {
                z = gVar2.equals(gVar) || (this.b && this.a.b() == gVar.a());
            } else {
                z = this.c.isAssignableFrom(gVar.a());
            }
            if (z) {
                return new TreeTypeAdapter(this.d, this.e, eVar, gVar, this);
            }
            return null;
        }
    }

    private final class a implements com.mnet.gson.i, r {
        private a() {
        }

        public k a(Object obj) {
            return TreeTypeAdapter.this.c.a(obj);
        }

        public <R> R a(k kVar, Type type) {
            return TreeTypeAdapter.this.c.a(kVar, type);
        }
    }
}
