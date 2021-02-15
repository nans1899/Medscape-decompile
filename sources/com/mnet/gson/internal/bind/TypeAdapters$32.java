package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;

class TypeAdapters$32 implements w {
    final /* synthetic */ Class a;
    final /* synthetic */ v b;

    TypeAdapters$32(Class cls, v vVar) {
        this.a = cls;
        this.b = vVar;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.a() == this.a) {
            return this.b;
        }
        return null;
    }

    public String toString() {
        return "Factory[type=" + this.a.getName() + ",adapter=" + this.b + "]";
    }
}
