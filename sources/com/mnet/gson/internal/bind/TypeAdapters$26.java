package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.sql.Timestamp;
import java.util.Date;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;

class TypeAdapters$26 implements w {
    TypeAdapters$26() {
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.a() != Timestamp.class) {
            return null;
        }
        final v<Date> a = eVar.a(Date.class);
        return new v<Timestamp>() {
            /* renamed from: a */
            public Timestamp read(h hVar) {
                Date date = (Date) a.read(hVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, Timestamp timestamp) {
                a.write(jVar, timestamp);
            }
        };
    }
}
