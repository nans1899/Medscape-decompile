package net.media.android.bidder.base.gson.adapter;

import com.mnet.gson.h;
import com.mnet.gson.i;
import com.mnet.gson.j;
import com.mnet.gson.k;
import com.mnet.gson.n;
import com.mnet.gson.q;
import com.mnet.gson.r;
import com.mnet.gson.s;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapSerializer implements j<Map<String, Object>>, s<Map<String, Object>> {
    /* renamed from: a */
    public Map<String, Object> b(k kVar, Type type, i iVar) {
        HashMap hashMap = new HashMap();
        n m = kVar.m();
        for (Map.Entry<String, k> key : m.a()) {
            String str = (String) key.getKey();
            k b = m.b(str);
            if (b.k()) {
                hashMap.put(str, a((q) b));
            } else if (b.j()) {
                hashMap.put(str, a(b.m(), iVar));
            } else if (b.i()) {
                hashMap.put(str, a(b.n(), iVar));
            }
        }
        return hashMap;
    }

    public k a(Map<String, Object> map, Type type, r rVar) {
        n nVar = new n();
        for (Map.Entry next : map.entrySet()) {
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
            } else {
                nVar.a(str, rVar.a(value));
            }
        }
        return nVar;
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
        throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.gson.adapter.MapSerializer.a(com.mnet.gson.q):java.lang.Object");
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
