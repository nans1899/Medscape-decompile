package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.sql.Date;
import mnetinternal.g;

class SqlDateTypeAdapter$1 implements w {
    SqlDateTypeAdapter$1() {
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.a() == Date.class) {
            return new f();
        }
        return null;
    }
}
