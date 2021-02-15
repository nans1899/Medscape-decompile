package mnetinternal;

import net.media.android.bidder.base.logging.Logger;

public final class cc {
    private static Class a;

    static {
        try {
            a = cc.class.getClassLoader().loadClass("android.widget.ListView");
        } catch (ClassNotFoundException unused) {
        }
    }

    private cc() {
    }

    public static boolean a(Object obj) {
        Class cls = a;
        return cls != null && cls.isInstance(obj);
    }

    public static int b(Object obj) {
        if (a == null) {
            return -1;
        }
        if (!a(obj)) {
            Logger.error("##ListViewReflect##", "Object is not a type of ListView: " + obj.getClass().getCanonicalName());
            return -1;
        }
        try {
            Object a2 = ce.a(obj, "getFirstVisiblePosition", (Class[]) null, new Object[0]);
            if (a2 == null) {
                return -1;
            }
            return ((Integer) a2).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    public static int c(Object obj) {
        if (a == null) {
            return -1;
        }
        if (!a(obj)) {
            Logger.error("##ListViewReflect##", "Object is not a type of ListView: " + obj.getClass().getCanonicalName());
            return -1;
        }
        try {
            Object a2 = ce.a(obj, "getLastVisiblePosition", (Class[]) null, new Object[0]);
            if (a2 == null) {
                return -1;
            }
            return ((Integer) a2).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }
}
