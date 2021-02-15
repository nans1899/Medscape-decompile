package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.mnet.gson.v;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import mnetinternal.g;
import mnetinternal.j;

final class h<T> extends v<T> {
    private final e a;
    private final v<T> b;
    private final Type c;

    h(e eVar, v<T> vVar, Type type) {
        this.a = eVar;
        this.b = vVar;
        this.c = type;
    }

    public T read(mnetinternal.h hVar) {
        return this.b.read(hVar);
    }

    public void write(j jVar, T t) {
        v<T> vVar = this.b;
        Type a2 = a(this.c, t);
        if (a2 != this.c) {
            vVar = this.a.a(g.a(a2));
            if (vVar instanceof ReflectiveTypeAdapterFactory.a) {
                v<T> vVar2 = this.b;
                if (!(vVar2 instanceof ReflectiveTypeAdapterFactory.a)) {
                    vVar = vVar2;
                }
            }
        }
        vVar.write(jVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        }
        return type;
    }
}
