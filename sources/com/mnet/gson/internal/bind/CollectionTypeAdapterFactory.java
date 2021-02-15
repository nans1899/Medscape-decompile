package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.internal.b;
import com.mnet.gson.internal.c;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.Type;
import java.util.Collection;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class CollectionTypeAdapterFactory implements w {
    private final c a;

    public CollectionTypeAdapterFactory(c cVar) {
        this.a = cVar;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        Type b = gVar.b();
        Class<? super T> a2 = gVar.a();
        if (!Collection.class.isAssignableFrom(a2)) {
            return null;
        }
        Type a3 = b.a(b, (Class<?>) a2);
        return new a(eVar, a3, eVar.a(g.a(a3)), this.a.a(gVar));
    }

    private static final class a<E> extends v<Collection<E>> {
        private final v<E> a;
        private final com.mnet.gson.internal.g<? extends Collection<E>> b;

        public a(e eVar, Type type, v<E> vVar, com.mnet.gson.internal.g<? extends Collection<E>> gVar) {
            this.a = new h(eVar, vVar, type);
            this.b = gVar;
        }

        /* renamed from: a */
        public Collection<E> read(h hVar) {
            if (hVar.f() == i.NULL) {
                hVar.j();
                return null;
            }
            Collection<E> collection = (Collection) this.b.a();
            hVar.a();
            while (hVar.e()) {
                collection.add(this.a.read(hVar));
            }
            hVar.b();
            return collection;
        }

        /* renamed from: a */
        public void write(j jVar, Collection<E> collection) {
            if (collection == null) {
                jVar.f();
                return;
            }
            jVar.b();
            for (E write : collection) {
                this.a.write(jVar, write);
            }
            jVar.c();
        }
    }
}
