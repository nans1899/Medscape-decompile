package net.media.android.bidder.base.gson.adapter;

import com.facebook.internal.NativeProtocol;
import com.mnet.gson.h;
import com.mnet.gson.i;
import com.mnet.gson.j;
import com.mnet.gson.k;
import com.mnet.gson.n;
import com.mnet.gson.r;
import com.mnet.gson.s;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import net.media.android.bidder.base.models.internal.Event;

public class EventJsonSerializer implements j<Event>, s<Event> {
    public k a(Event event, Type type, r rVar) {
        Map<String, Object> userProps = event.getUserProps();
        n nVar = new n();
        nVar.a("ifa", event.getAdvertisingId());
        nVar.a("event_type", event.getType());
        nVar.a(NativeProtocol.WEB_DIALOG_PARAMS, rVar.a(event.getParams()));
        nVar.a("event_timestamp", (Number) Long.valueOf(event.getTimestamp()));
        for (Map.Entry next : userProps.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (value instanceof Integer) {
                nVar.a(str, (Number) Integer.valueOf(((Integer) value).intValue()));
            } else if (value instanceof Float) {
                nVar.a(str, (Number) Float.valueOf(((Float) value).floatValue()));
            } else if (value instanceof Long) {
                nVar.a(str, (Number) Long.valueOf(((Long) value).longValue()));
            } else if (value instanceof String) {
                nVar.a(str, (String) value);
            } else if (value instanceof Double) {
                nVar.a(str, (Number) Double.valueOf(((Double) value).doubleValue()));
            }
        }
        return nVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.util.Map} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.media.android.bidder.base.models.internal.Event b(com.mnet.gson.k r7, java.lang.reflect.Type r8, com.mnet.gson.i r9) {
        /*
            r6 = this;
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            com.mnet.gson.n r7 = r7.m()
            java.util.Set r0 = r7.a()
            java.util.Iterator r0 = r0.iterator()
        L_0x0011:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0061
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r1 = r1.getKey()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r6.a((java.lang.String) r1)
            if (r2 == 0) goto L_0x0011
            com.mnet.gson.k r2 = r7.b(r1)
            boolean r3 = r2.k()
            if (r3 == 0) goto L_0x003d
            com.mnet.gson.q r2 = (com.mnet.gson.q) r2
            java.lang.Object r2 = r6.a((com.mnet.gson.q) r2)
            r8.put(r1, r2)
            goto L_0x0011
        L_0x003d:
            boolean r3 = r2.j()
            if (r3 == 0) goto L_0x004f
            com.mnet.gson.n r2 = r2.m()
            java.lang.Object r2 = r6.a((com.mnet.gson.n) r2, (com.mnet.gson.i) r9)
            r8.put(r1, r2)
            goto L_0x0011
        L_0x004f:
            boolean r3 = r2.i()
            if (r3 == 0) goto L_0x0011
            com.mnet.gson.h r2 = r2.n()
            java.lang.Object r2 = r6.a((com.mnet.gson.h) r2, (com.mnet.gson.i) r9)
            r8.put(r1, r2)
            goto L_0x0011
        L_0x0061:
            java.lang.String r0 = "ifa"
            boolean r1 = mnetinternal.da.a((com.mnet.gson.n) r7, (java.lang.String) r0)
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x0074
            com.mnet.gson.k r0 = r7.b(r0)
            java.lang.String r0 = r0.c()
            goto L_0x0075
        L_0x0074:
            r0 = r2
        L_0x0075:
            java.lang.String r1 = "event_type"
            boolean r3 = mnetinternal.da.a((com.mnet.gson.n) r7, (java.lang.String) r1)
            if (r3 == 0) goto L_0x0085
            com.mnet.gson.k r1 = r7.b(r1)
            java.lang.String r2 = r1.c()
        L_0x0085:
            r1 = 0
            java.lang.String r3 = "params"
            boolean r4 = mnetinternal.da.a((com.mnet.gson.n) r7, (java.lang.String) r3)
            if (r4 == 0) goto L_0x00a6
            com.mnet.gson.k r1 = r7.b(r3)
            com.mnet.gson.n r1 = r1.m()
            net.media.android.bidder.base.gson.adapter.EventJsonSerializer$1 r3 = new net.media.android.bidder.base.gson.adapter.EventJsonSerializer$1
            r3.<init>()
            java.lang.reflect.Type r3 = r3.b()
            java.lang.Object r9 = r9.a(r1, r3)
            r1 = r9
            java.util.Map r1 = (java.util.Map) r1
        L_0x00a6:
            r3 = 0
            java.lang.String r9 = "event_timestamp"
            boolean r5 = mnetinternal.da.a((com.mnet.gson.n) r7, (java.lang.String) r9)
            if (r5 == 0) goto L_0x00b8
            com.mnet.gson.k r7 = r7.b(r9)
            long r3 = r7.f()
        L_0x00b8:
            net.media.android.bidder.base.models.internal.Event$Builder r7 = new net.media.android.bidder.base.models.internal.Event$Builder
            r7.<init>()
            net.media.android.bidder.base.models.internal.Event$Builder r7 = r7.setAdvertisingId(r0)
            net.media.android.bidder.base.models.internal.Event$Builder r7 = r7.setType(r2)
            net.media.android.bidder.base.models.internal.Event$Builder r7 = r7.setParams(r1)
            net.media.android.bidder.base.models.internal.Event$Builder r7 = r7.setUserProperties(r8)
            net.media.android.bidder.base.models.internal.Event$Builder r7 = r7.setTimestamp(r3)
            net.media.android.bidder.base.models.internal.Event r7 = r7.build()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.gson.adapter.EventJsonSerializer.b(com.mnet.gson.k, java.lang.reflect.Type, com.mnet.gson.i):net.media.android.bidder.base.models.internal.Event");
    }

    private boolean a(String str) {
        return !"ifa".equals(str) && !"event_type".equals(str) && !NativeProtocol.WEB_DIALOG_PARAMS.equals(str) && !"event_timestamp".equals(str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:14|15|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        return java.lang.Long.valueOf(r3.longValue());
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object a(com.mnet.gson.q r3) {
        /*
            r2 = this;
            boolean r0 = r3.a()
            if (r0 == 0) goto L_0x000f
            boolean r3 = r3.h()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            return r3
        L_0x000f:
            boolean r0 = r3.s()
            if (r0 == 0) goto L_0x001a
            java.lang.String r3 = r3.c()
            return r3
        L_0x001a:
            java.math.BigDecimal r3 = r3.e()
            r3.toBigIntegerExact()     // Catch:{ ArithmeticException -> 0x0033 }
            int r0 = r3.intValueExact()     // Catch:{ ArithmeticException -> 0x002a }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)     // Catch:{ ArithmeticException -> 0x002a }
            return r3
        L_0x002a:
            long r0 = r3.longValue()     // Catch:{ ArithmeticException -> 0x0033 }
            java.lang.Long r3 = java.lang.Long.valueOf(r0)     // Catch:{ ArithmeticException -> 0x0033 }
            return r3
        L_0x0033:
            double r0 = r3.doubleValue()
            java.lang.Double r3 = java.lang.Double.valueOf(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.gson.adapter.EventJsonSerializer.a(com.mnet.gson.q):java.lang.Object");
    }

    private Object a(h hVar, i iVar) {
        int a = hVar.a();
        Object[] objArr = new Object[a];
        for (int i = 0; i < a; i++) {
            objArr[i] = iVar.a(hVar.a(i), Object.class);
        }
        return objArr;
    }

    private Object a(n nVar, i iVar) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : nVar.a()) {
            hashMap.put(next.getKey(), iVar.a((k) next.getValue(), Object.class));
        }
        return hashMap;
    }
}
