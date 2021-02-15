package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;

class TypeAdapters$34 implements w {
    final /* synthetic */ Class a;
    final /* synthetic */ Class b;
    final /* synthetic */ v c;

    TypeAdapters$34(Class cls, Class cls2, v vVar) {
        this.a = cls;
        this.b = cls2;
        this.c = vVar;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        Class<? super T> a2 = gVar.a();
        if (a2 == this.a || a2 == this.b) {
            return this.c;
        }
        return null;
    }

    public String toString() {
        return "Factory[type=" + this.a.getName() + "+" + this.b.getName() + ",adapter=" + this.c + "]";
    }
}
