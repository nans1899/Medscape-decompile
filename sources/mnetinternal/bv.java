package mnetinternal;

import java.lang.reflect.Method;

public final class bv {
    private static Class a = bu.a("androidx.viewpager.widget.ViewPager");
    private static Class b;
    private static Method c;
    private static Method d = bu.b(a, "getAdapter", new Class[0]);
    private static Method e = bu.b(a, "getCurrentItem", new Class[0]);
    private static Class f = bu.a("androidx.fragment.app.FragmentPagerAdapter");
    private static Class g = bu.a("androidx.legacy.app.FragmentStatePagerAdapter");

    static {
        Class a2 = bu.a("androidx.viewpager.widget.PagerAdapter");
        b = a2;
        c = bu.b(a2, "getCount", new Class[0]);
    }

    public static boolean a(Object obj) {
        Class cls = a;
        return cls != null && cls.isInstance(obj);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|(1:18)(2:19|20)) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1 = e(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r1 == null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        return ((java.lang.Integer) r1.invoke(r3, new java.lang.Object[0])).intValue();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(java.lang.Object r3) {
        /*
            r0 = 0
            java.lang.Object r3 = d(r3)     // Catch:{ Exception -> 0x0043 }
            if (r3 != 0) goto L_0x0008
            return r0
        L_0x0008:
            java.lang.reflect.Method r1 = c     // Catch:{ Exception -> 0x0043 }
            if (r1 != 0) goto L_0x0020
            java.lang.reflect.Method r1 = e(r3)     // Catch:{ Exception -> 0x0043 }
            if (r1 != 0) goto L_0x0013
            return r0
        L_0x0013:
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r3 = r1.invoke(r3, r2)     // Catch:{ Exception -> 0x0043 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x0043 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0043 }
            return r3
        L_0x0020:
            java.lang.reflect.Method r1 = c     // Catch:{ Exception -> 0x002f }
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x002f }
            java.lang.Object r1 = r1.invoke(r3, r2)     // Catch:{ Exception -> 0x002f }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x002f }
            int r3 = r1.intValue()     // Catch:{ Exception -> 0x002f }
            return r3
        L_0x002f:
            java.lang.reflect.Method r1 = e(r3)     // Catch:{ Exception -> 0x0043 }
            if (r1 != 0) goto L_0x0036
            return r0
        L_0x0036:
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r3 = r1.invoke(r3, r2)     // Catch:{ Exception -> 0x0043 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x0043 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0043 }
            return r3
        L_0x0043:
            r3 = move-exception
            java.lang.String r1 = r3.getMessage()
            java.lang.String r2 = "##ViewPagerUtils"
            mnetinternal.bi.a(r2, r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.bv.b(java.lang.Object):int");
    }

    private static Object d(Object obj) {
        Method method = d;
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            bi.a("##ViewPagerUtils", e2.getMessage(), e2);
            return null;
        }
    }

    private static Method e(Object obj) {
        Class<?> cls;
        if (!(obj == null || (cls = obj.getClass()) == null)) {
            for (Method method : bu.a((Class) cls)) {
                if (method.getName().equals("getCount")) {
                    return method;
                }
                if (method.getReturnType() == Integer.TYPE && method.getGenericParameterTypes().length == 0) {
                    return method;
                }
            }
        }
        return null;
    }

    public static int c(Object obj) {
        try {
            if (e != null) {
                return ((Integer) e.invoke(obj, new Object[0])).intValue();
            }
        } catch (Exception e2) {
            bi.a("##ViewPagerUtils", e2.getMessage(), e2);
        }
        return 0;
    }
}
