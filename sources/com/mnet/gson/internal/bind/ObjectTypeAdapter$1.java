package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;

class ObjectTypeAdapter$1 implements w {
    ObjectTypeAdapter$1() {
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.a() == Object.class) {
            return new e(eVar);
        }
        return null;
    }
}
