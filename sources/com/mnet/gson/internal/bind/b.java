package com.mnet.gson.internal.bind;

import com.mnet.gson.v;
import com.mnet.gson.w;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class b extends v<Date> {
    public static final w a = new DateTypeAdapter$1();
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: a */
    public Date read(h hVar) {
        if (hVar.f() != i.NULL) {
            return a(hVar.h());
        }
        hVar.j();
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return mnetinternal.f.a(r3, new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        throw new com.mnet.gson.t(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        return r2.b.parse(r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0013 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.Date a(java.lang.String r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.text.DateFormat r0 = r2.c     // Catch:{ ParseException -> 0x000b }
            java.util.Date r3 = r0.parse(r3)     // Catch:{ ParseException -> 0x000b }
            monitor-exit(r2)
            return r3
        L_0x0009:
            r3 = move-exception
            goto L_0x0026
        L_0x000b:
            java.text.DateFormat r0 = r2.b     // Catch:{ ParseException -> 0x0013 }
            java.util.Date r3 = r0.parse(r3)     // Catch:{ ParseException -> 0x0013 }
            monitor-exit(r2)
            return r3
        L_0x0013:
            java.text.ParsePosition r0 = new java.text.ParsePosition     // Catch:{ ParseException -> 0x001f }
            r1 = 0
            r0.<init>(r1)     // Catch:{ ParseException -> 0x001f }
            java.util.Date r3 = mnetinternal.f.a((java.lang.String) r3, (java.text.ParsePosition) r0)     // Catch:{ ParseException -> 0x001f }
            monitor-exit(r2)
            return r3
        L_0x001f:
            r0 = move-exception
            com.mnet.gson.t r1 = new com.mnet.gson.t     // Catch:{ all -> 0x0009 }
            r1.<init>(r3, r0)     // Catch:{ all -> 0x0009 }
            throw r1     // Catch:{ all -> 0x0009 }
        L_0x0026:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.internal.bind.b.a(java.lang.String):java.util.Date");
    }

    /* renamed from: a */
    public synchronized void write(j jVar, Date date) {
        if (date == null) {
            jVar.f();
        } else {
            jVar.b(this.b.format(date));
        }
    }
}
