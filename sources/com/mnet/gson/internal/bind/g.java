package com.mnet.gson.internal.bind;

import com.mnet.gson.t;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class g extends v<Time> {
    public static final w a = new TimeTypeAdapter$1();
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized Time read(h hVar) {
        if (hVar.f() == i.NULL) {
            hVar.j();
            return null;
        }
        try {
            return new Time(this.b.parse(hVar.h()).getTime());
        } catch (ParseException e) {
            throw new t((Throwable) e);
        }
    }

    /* renamed from: a */
    public synchronized void write(j jVar, Time time) {
        jVar.b(time == null ? null : this.b.format(time));
    }
}
