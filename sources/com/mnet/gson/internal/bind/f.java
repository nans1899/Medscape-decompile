package com.mnet.gson.internal.bind;

import com.mnet.gson.t;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class f extends v<Date> {
    public static final w a = new SqlDateTypeAdapter$1();
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized Date read(h hVar) {
        if (hVar.f() == i.NULL) {
            hVar.j();
            return null;
        }
        try {
            return new Date(this.b.parse(hVar.h()).getTime());
        } catch (ParseException e) {
            throw new t((Throwable) e);
        }
    }

    /* renamed from: a */
    public synchronized void write(j jVar, Date date) {
        jVar.b(date == null ? null : this.b.format(date));
    }
}
