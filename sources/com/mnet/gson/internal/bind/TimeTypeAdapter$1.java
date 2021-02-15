package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.sql.Time;
import mnetinternal.g;

class TimeTypeAdapter$1 implements w {
    TimeTypeAdapter$1() {
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.a() == Time.class) {
            return new g();
        }
        return null;
    }
}
