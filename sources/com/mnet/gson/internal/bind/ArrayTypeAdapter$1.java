package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.internal.b;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import mnetinternal.g;

class ArrayTypeAdapter$1 implements w {
    ArrayTypeAdapter$1() {
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        Type b = gVar.b();
        if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
            return null;
        }
        Type g = b.g(b);
        return new a(eVar, eVar.a(g.a(g)), b.e(g));
    }
}
