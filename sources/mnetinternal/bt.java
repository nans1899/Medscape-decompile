package mnetinternal;

import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class bt {
    private static Class a = bu.a("androidx.recyclerview.widget.RecyclerView");
    private static Class b = bu.a("androidx.recyclerview.widget.RecyclerView$Adapter");
    private static Class c = bu.a("androidx.recyclerview.widget.RecyclerView$ViewHolder");
    private static Method d = bu.b(b, "onCreateViewHolder", ViewGroup.class, Integer.TYPE);
    private static Method e = bu.b(b, "onBindViewHolder", c, Integer.TYPE);
    private static Method f = bu.b(a, "getAdapter", new Class[0]);
    private static Method g = bu.b(b, "getItemCount", new Class[0]);
    private static Field h = bu.a(c, "itemView");
    private static Method i = bu.b(b, "getItemViewType", Integer.TYPE);

    public static boolean a(Object obj) {
        Class cls = a;
        return cls != null && cls.isInstance(obj);
    }

    public static boolean b(Object obj) {
        Object g2;
        if (a == null || (g2 = g(obj)) == null || d(g2) == null) {
            return false;
        }
        bi.a("##RecyclerViewUtils", "We can process recyclerView");
        return true;
    }

    private static Method d(Object obj) {
        Method method = g;
        if (method != null) {
            return method;
        }
        if (obj == null) {
            return null;
        }
        Class<?> cls = b;
        if (cls == null) {
            cls = obj.getClass();
        }
        if (cls != null) {
            for (Method method2 : bu.a((Class) cls)) {
                if (method2.getReturnType() == Integer.TYPE && Modifier.isAbstract(method2.getModifiers())) {
                    return method2;
                }
            }
        }
        return bu.a(obj.getClass(), "getItemCount", new Class[0]);
    }

    private static Method e(Object obj) {
        if (obj == null) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls != null) {
            for (Method method : bu.a((Class) cls)) {
                if (method.getReturnType() == Integer.TYPE && Modifier.isAbstract(method.getModifiers())) {
                    return method;
                }
            }
        }
        return bu.a(obj.getClass(), "getItemCount", new Class[0]);
    }

    private static Method f(Object obj) {
        Method method = f;
        if (method != null) {
            return method;
        }
        if (obj == null) {
            return null;
        }
        for (Method method2 : bu.a(a)) {
            if (method2.getReturnType() == b) {
                return method2;
            }
        }
        return bu.a(obj.getClass(), "getAdapter", new Class[0]);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:10|11|(1:13)(2:14|15)) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1 = e(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r1 == null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        return ((java.lang.Integer) r1.invoke(r3, new java.lang.Object[0])).intValue();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int c(java.lang.Object r3) {
        /*
            r0 = 0
            java.lang.Object r3 = g(r3)     // Catch:{ Exception -> 0x0043 }
            if (r3 != 0) goto L_0x0008
            return r0
        L_0x0008:
            java.lang.reflect.Method r1 = g     // Catch:{ Exception -> 0x0043 }
            if (r1 == 0) goto L_0x002f
            java.lang.reflect.Method r1 = g     // Catch:{ Exception -> 0x001b }
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x001b }
            java.lang.Object r1 = r1.invoke(r3, r2)     // Catch:{ Exception -> 0x001b }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x001b }
            int r3 = r1.intValue()     // Catch:{ Exception -> 0x001b }
            return r3
        L_0x001b:
            java.lang.reflect.Method r1 = e(r3)     // Catch:{ Exception -> 0x0043 }
            if (r1 != 0) goto L_0x0022
            return r0
        L_0x0022:
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r3 = r1.invoke(r3, r2)     // Catch:{ Exception -> 0x0043 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x0043 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0043 }
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
            java.lang.String r1 = "##RecyclerViewUtils"
            mnetinternal.bi.a((java.lang.String) r1, (java.lang.Throwable) r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.bt.c(java.lang.Object):int");
    }

    private static Object g(Object obj) {
        Method f2 = f(obj);
        if (f2 == null) {
            return null;
        }
        try {
            return f2.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            bi.a("##RecyclerViewUtils", e2.getMessage(), e2);
            return null;
        }
    }
}
