package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.Array;
import java.util.ArrayList;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class a<E> extends v<Object> {
    public static final w a = new ArrayTypeAdapter$1();
    private final Class<E> b;
    private final v<E> c;

    public a(e eVar, v<E> vVar, Class<E> cls) {
        this.c = new h(eVar, vVar, cls);
        this.b = cls;
    }

    public Object read(h hVar) {
        if (hVar.f() == i.NULL) {
            hVar.j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        hVar.a();
        while (hVar.e()) {
            arrayList.add(this.c.read(hVar));
        }
        hVar.b();
        Object newInstance = Array.newInstance(this.b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void write(j jVar, Object obj) {
        if (obj == null) {
            jVar.f();
            return;
        }
        jVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.write(jVar, Array.get(obj, i));
        }
        jVar.c();
    }
}
