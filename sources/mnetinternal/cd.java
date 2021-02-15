package mnetinternal;

import net.media.android.bidder.base.logging.Logger;

public final class cd {
    private static Class a;

    static {
        try {
            a = cd.class.getClassLoader().loadClass("androidx.recyclerview.widget.RecyclerView");
        } catch (ClassNotFoundException unused) {
        }
    }

    private cd() {
    }

    public static boolean a(Object obj) {
        Class cls = a;
        return cls != null && cls.isInstance(obj);
    }

    public static int b(Object obj) {
        Object a2;
        if (a == null) {
            return -1;
        }
        if (!a(obj)) {
            Logger.error("##RecyclerViewReflect##", "Object is not a type of RecyclerView: " + obj.getClass().getCanonicalName());
            return -1;
        }
        try {
            Object a3 = ce.a(obj, "getLayoutManager", (Class[]) null, new Object[0]);
            if (a3 == null || (a2 = ce.a(a3, "findFirstVisibleItemPosition", (Class[]) null, new Object[0])) == null) {
                return -1;
            }
            return ((Integer) a2).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    public static int c(Object obj) {
        Object a2;
        if (a == null) {
            return -1;
        }
        if (!a(obj)) {
            Logger.error("##RecyclerViewReflect##", "Object is not a type of RecyclerView: " + obj.getClass().getCanonicalName());
            return -1;
        }
        try {
            Object a3 = ce.a(obj, "getLayoutManager", (Class[]) null, new Object[0]);
            if (a3 == null || (a2 = ce.a(a3, "findLastVisibleItemPosition", (Class[]) null, new Object[0])) == null) {
                return -1;
            }
            return ((Integer) a2).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }
}
