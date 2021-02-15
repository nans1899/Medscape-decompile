package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;

class TypeAdapters$31 implements w {
    final /* synthetic */ g a;
    final /* synthetic */ v b;

    TypeAdapters$31(g gVar, v vVar) {
        this.a = gVar;
        this.b = vVar;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.equals(this.a)) {
            return this.b;
        }
        return null;
    }
}
