package com.mnet.gson;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class DefaultDateTypeAdapter implements j<Date>, s<Date> {
    private final DateFormat a;
    private final DateFormat b;

    DefaultDateTypeAdapter() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    DefaultDateTypeAdapter(String str) {
        this((DateFormat) new SimpleDateFormat(str, Locale.US), (DateFormat) new SimpleDateFormat(str));
    }

    public DefaultDateTypeAdapter(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    DefaultDateTypeAdapter(DateFormat dateFormat, DateFormat dateFormat2) {
        this.a = dateFormat;
        this.b = dateFormat2;
    }

    public k a(Date date, Type type, r rVar) {
        q qVar;
        synchronized (this.b) {
            qVar = new q(this.a.format(date));
        }
        return qVar;
    }

    /* renamed from: a */
    public Date b(k kVar, Type type, i iVar) {
        if (kVar instanceof q) {
            Date a2 = a(kVar);
            if (type == Date.class) {
                return a2;
            }
            if (type == Timestamp.class) {
                return new Timestamp(a2.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(a2.getTime());
            }
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
        }
        throw new o("The date should be a string value");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:8|9|10|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r5 = mnetinternal.f.a(r5.c(), new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        throw new com.mnet.gson.t(r5.c(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r5 = r4.a.parse(r5.c());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Date a(com.mnet.gson.k r5) {
        /*
            r4 = this;
            java.text.DateFormat r0 = r4.b
            monitor-enter(r0)
            java.text.DateFormat r1 = r4.b     // Catch:{ ParseException -> 0x0011 }
            java.lang.String r2 = r5.c()     // Catch:{ ParseException -> 0x0011 }
            java.util.Date r5 = r1.parse(r2)     // Catch:{ ParseException -> 0x0011 }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r5
        L_0x000f:
            r5 = move-exception
            goto L_0x0038
        L_0x0011:
            java.text.DateFormat r1 = r4.a     // Catch:{ ParseException -> 0x001d }
            java.lang.String r2 = r5.c()     // Catch:{ ParseException -> 0x001d }
            java.util.Date r5 = r1.parse(r2)     // Catch:{ ParseException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r5
        L_0x001d:
            java.lang.String r1 = r5.c()     // Catch:{ ParseException -> 0x002d }
            java.text.ParsePosition r2 = new java.text.ParsePosition     // Catch:{ ParseException -> 0x002d }
            r3 = 0
            r2.<init>(r3)     // Catch:{ ParseException -> 0x002d }
            java.util.Date r5 = mnetinternal.f.a((java.lang.String) r1, (java.text.ParsePosition) r2)     // Catch:{ ParseException -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r5
        L_0x002d:
            r1 = move-exception
            com.mnet.gson.t r2 = new com.mnet.gson.t     // Catch:{ all -> 0x000f }
            java.lang.String r5 = r5.c()     // Catch:{ all -> 0x000f }
            r2.<init>(r5, r1)     // Catch:{ all -> 0x000f }
            throw r2     // Catch:{ all -> 0x000f }
        L_0x0038:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.DefaultDateTypeAdapter.a(com.mnet.gson.k):java.util.Date");
    }

    public String toString() {
        return DefaultDateTypeAdapter.class.getSimpleName() + ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN + this.b.getClass().getSimpleName() + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
