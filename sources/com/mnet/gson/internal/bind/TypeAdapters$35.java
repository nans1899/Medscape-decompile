package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.t;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;

class TypeAdapters$35 implements w {
    final /* synthetic */ Class a;
    final /* synthetic */ v b;

    TypeAdapters$35(Class cls, v vVar) {
        this.a = cls;
        this.b = vVar;
    }

    public <T2> v<T2> create(e eVar, g<T2> gVar) {
        final Class<? super T2> a2 = gVar.a();
        if (!this.a.isAssignableFrom(a2)) {
            return null;
        }
        return new v<T1>() {
            public void write(j jVar, T1 t1) {
                TypeAdapters$35.this.b.write(jVar, t1);
            }

            public T1 read(h hVar) {
                T1 read = TypeAdapters$35.this.b.read(hVar);
                if (read == null || a2.isInstance(read)) {
                    return read;
                }
                throw new t("Expected a " + a2.getName() + " but was " + read.getClass().getName());
            }
        };
    }

    public String toString() {
        return "Factory[typeHierarchy=" + this.a.getName() + ",adapter=" + this.b + "]";
    }
}
